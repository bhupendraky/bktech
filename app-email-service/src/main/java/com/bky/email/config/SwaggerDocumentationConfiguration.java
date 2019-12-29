package com.bky.email.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfiguration {

	@Value("${swagger.base-package}")
	private String basePackage;

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Email Service REST API")
				.description("Sample REST API for centalized documentation using Spring Boot and spring-fox swagger 2 ")
				.termsOfServiceUrl("#")
				.version("0.0.1-SNAPSHOT")
				.contact(new Contact(
						"Bhupendra Kumar",
						"https://www.google.com",
						"bhupendraky@gmail.com"))
				.build();
	}

	@Bean
	public Docket configureControllerPackageAndConvertors() {

		Parameter parameter = new ParameterBuilder()
				.name("UserId")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.build();

		ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any());

		Optional.ofNullable(basePackage).ifPresent(pkg -> {
			builder.apis(RequestHandlerSelectors.basePackage(pkg));
		});

		return builder.build().apiInfo(apiInfo())
				.globalOperationParameters(Lists.newArrayList(parameter));
	}

}
