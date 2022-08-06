package com.techy.url;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.spring4all.swagger.EnableSwagger2Doc;
import com.spring4all.swagger.SwaggerProperties;
import com.techy.common.config.SwaggerPropertiesInitializer;
import com.techy.common.ctx.AuditorAwareImpl;
import com.techy.common.ctx.RequestInterceptorImpl;

import feign.RequestInterceptor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@EnableJpaRepositories
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class AppUrlServiceApplication implements InitializingBean {

	public static void main(String[] args) {
		SpringApplication.run(AppUrlServiceApplication.class, args);
	}

	@Autowired
	private SwaggerProperties swaggerProperties;

	@Override
	public void afterPropertiesSet() throws Exception {
		SwaggerPropertiesInitializer.configureSwaggerHeader(swaggerProperties);
	}

	@Bean
	public AuditorAwareImpl auditorAwareImpl() {
		return new AuditorAwareImpl();
	}

	@Bean
	public RequestInterceptor requestInterceptor() {
		return new RequestInterceptorImpl();
	}
}