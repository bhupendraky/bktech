package com.bky.email;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.spring4all.swagger.EnableSwagger2Doc;
import com.spring4all.swagger.SwaggerProperties;
import com.spring4all.swagger.SwaggerProperties.GlobalOperationParameter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
public class AppEmailServiceApplication implements InitializingBean {

	public static void main(String[] args) {
		SpringApplication.run(AppEmailServiceApplication.class, args);
	}

	@Autowired
	private SwaggerProperties swaggerProperties;

	@Override
	public void afterPropertiesSet() throws Exception {
		GlobalOperationParameter userId = new GlobalOperationParameter();
		userId.setName("UserId");
		userId.setParameterType("header");
		userId.setModelRef("string");

		if(CollectionUtils.isEmpty(swaggerProperties.getGlobalOperationParameters())){
			swaggerProperties.setGlobalOperationParameters(Lists.newArrayList(userId));
		} else {
			swaggerProperties.getGlobalOperationParameters().add(userId);
		}
	}

}
