package com.techy.common.config;

import com.google.common.collect.Lists;
import com.spring4all.swagger.SwaggerProperties;
import com.spring4all.swagger.SwaggerProperties.GlobalOperationParameter;
import com.techy.common.enums.Globals;

public class SwaggerPropertiesInitializer {

	public static void configureSwaggerHeader(SwaggerProperties swaggerProperties) throws Exception {
		GlobalOperationParameter userId = new GlobalOperationParameter();
		userId.setName(Globals.HTTP_HEADER_USER_ID.value());
		userId.setDescription("User ID");
		userId.setParameterType("header");
		userId.setModelRef("string");
		userId.setRequired("true");

		if(swaggerProperties.getGlobalOperationParameters() == null){
			swaggerProperties.setGlobalOperationParameters(Lists.newArrayList(userId));
		} else {
			swaggerProperties.getGlobalOperationParameters().add(userId);
		}
	}
}
