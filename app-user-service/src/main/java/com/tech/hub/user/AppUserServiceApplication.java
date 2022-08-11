package com.tech.hub.user;

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
import com.tech.hub.common.config.SwaggerPropertiesInitializer;
import com.tech.hub.common.ctx.AuditorAwareImpl;
import com.tech.hub.common.ctx.RequestInterceptorImpl;

import feign.RequestInterceptor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@EnableJpaRepositories
@EnableFeignClients(basePackages = {"com.tech.hub.user.proxy"})
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class AppUserServiceApplication implements InitializingBean {

	public static void main(String[] args) {
		SpringApplication.run(AppUserServiceApplication.class, args);
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
