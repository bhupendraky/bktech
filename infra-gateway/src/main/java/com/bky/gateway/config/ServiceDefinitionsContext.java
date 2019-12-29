package com.bky.gateway.config;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.swagger.web.SwaggerResource;


@Component
public class ServiceDefinitionsContext {

	private static final Logger logger = LoggerFactory.getLogger(ServiceDefinitionsContext.class);

	private ObjectMapper mapper = new ObjectMapper();

	private final HashMap<String, String> serviceDescriptions = new HashMap<String, String>();


	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate template;

	public String getSwaggerDefinition(String serviceId) {
		return this.serviceDescriptions.get(serviceId);
	}

	public List<SwaggerResource> getDocumentationConfigs() {
		updateConfigurations();
		List<SwaggerResource> resources = serviceDescriptions.entrySet().stream()
				.filter(service -> !service.getKey().equals("infra-gateway"))
				.map(e -> createResource(e.getKey()))
				.collect(Collectors.toList());
		if(resources.isEmpty()) {
			resources.add(createResource("default"));
		}
		return resources;
	}

	private SwaggerResource createResource(String name) {
		SwaggerResource resource = new SwaggerResource();
		resource.setLocation(String.format("/%s/v2/api-docs", name));
		resource.setName(name);
		resource.setSwaggerVersion("2.0");
		return resource;
	}

	private void updateConfigurations() {
		logger.debug("Starting Service Definition Context refresh");
		discoveryClient.getServices().stream()
		.forEach(serviceId -> {
			List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
			if (!CollectionUtils.isEmpty(serviceInstances)) {
				String swaggerURL = getSwaggerURL(serviceInstances.get(0));
				getSwaggerDefinitionForAPI(serviceId, swaggerURL)
				.ifPresent(data -> {
					serviceDescriptions.put(serviceId, getJSON(serviceId, data));
				});
			}
		});
	}

	private String getSwaggerURL(ServiceInstance instance) {
		return instance.getUri() +
				Optional.ofNullable(instance.getMetadata().get("swagger_url"))
		.orElse("/v2/api-docs");
	}

	private Optional<Object> getSwaggerDefinitionForAPI(String serviceName, String url) {
		try {
			Object jsonData = template.getForObject(url, Object.class);
			return Optional.of(jsonData);
		} catch (RestClientException ex) {
			logger.error("Error while getting service definition for service : {} Error : {} ", serviceName,
					ex.getMessage());
			return Optional.empty();
		}
	}

	public String getJSON(String serviceId, Object jsonData) {
		String json = "";
		try {
			json = mapper.writeValueAsString(jsonData);
		} catch (JsonProcessingException e) {
			logger.error("Error : {} ", e.getMessage());
		}
		return json;
	}

}