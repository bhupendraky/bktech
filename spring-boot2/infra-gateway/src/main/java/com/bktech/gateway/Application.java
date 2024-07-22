package com.bktech.gateway;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.spring4all.swagger.EnableSwagger2Doc;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@EnableZuulProxy
@EnableSwagger2Doc
@EnableDiscoveryClient
@SpringBootApplication
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${spring.application.name}")
	private String appName;

	@Primary
	@Component
	class DocumentationConfig implements SwaggerResourcesProvider {

		private Predicate<String> predicate = name -> name.equals(appName);

		@Override
		public List<SwaggerResource> get() {
			List<SwaggerResource> resources = discoveryClient.getServices().stream()
					.filter(predicate.negate())
					.map(this::createResource)
					.collect(Collectors.toList());
			if (resources.isEmpty()) {
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
	}

}