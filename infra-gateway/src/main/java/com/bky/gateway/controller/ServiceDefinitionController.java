package com.bky.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bky.gateway.config.ServiceDefinitionsContext;

@RestController
public class ServiceDefinitionController {

	@Autowired
	private ServiceDefinitionsContext definitionContext;

	@GetMapping("/{serviceName}/v2/api-docs")
	public String getServiceDefinition(@PathVariable(value = "serviceName") String serviceName) {
		return definitionContext.getSwaggerDefinition(serviceName);
	}
}
