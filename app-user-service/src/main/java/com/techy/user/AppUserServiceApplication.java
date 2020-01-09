package com.techy.user;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.spring4all.swagger.EnableSwagger2Doc;
import com.spring4all.swagger.SwaggerProperties;
import com.spring4all.swagger.SwaggerProperties.GlobalOperationParameter;
import com.techy.user.enums.Constants;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class AppUserServiceApplication implements InitializingBean {

	public static void main(String[] args) {
		SpringApplication.run(AppUserServiceApplication.class, args);
	}

	@Autowired
	private SwaggerProperties swaggerProperties;

	@Override
	public void afterPropertiesSet() throws Exception {
		GlobalOperationParameter userId = new GlobalOperationParameter();
		userId.setName(Constants.HTTP_HEADER_ATTR_USER_ID.value());
		userId.setDescription("User ID");
		userId.setParameterType("header");
		userId.setModelRef("string");
		userId.setRequired("true");

		if(CollectionUtils.isEmpty(swaggerProperties.getGlobalOperationParameters())){
			swaggerProperties.setGlobalOperationParameters(Lists.newArrayList(userId));
		} else {
			swaggerProperties.getGlobalOperationParameters().add(userId);
		}
	}
}
