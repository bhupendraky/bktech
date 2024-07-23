package com.bktech.fin.config;

import com.bktech.fin.Constants;
import com.google.common.collect.Lists;
import com.spring4all.swagger.SwaggerProperties;
import com.spring4all.swagger.SwaggerProperties.GlobalOperationParameter;

public class SwaggerPropertiesInitializer {

	public static void configureSwaggerHeader(SwaggerProperties swaggerProperties) throws Exception {
		GlobalOperationParameter userId = new GlobalOperationParameter();
		userId.setName(Constants.REQ_HEADER_USER_ID);
		userId.setDescription("User ID");
		userId.setParameterType("header");
		userId.setModelRef("string");
		userId.setRequired("true");

		if (swaggerProperties.getGlobalOperationParameters() == null){
			swaggerProperties.setGlobalOperationParameters(Lists.newArrayList(userId));
		} else {
			swaggerProperties.getGlobalOperationParameters().add(userId);
		}
	}
}
