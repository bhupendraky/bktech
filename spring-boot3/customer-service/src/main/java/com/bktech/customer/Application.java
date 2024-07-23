package com.bktech.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bktech.customer.ctx.AppContext;
import com.bktech.customer.ctx.CacheKey;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@EnableFeignClients(basePackages = {"com.bktech.customer.proxy"})
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private ApplicationContext appCtx;
	private static final Map<CacheKey, ApplicationContext> contextMap = new HashMap<>();

	public static AppContext getContext() {
		return getSpringCtx().getBean(AppContext.class);
	}

	public static ApplicationContext getSpringCtx() {
		return contextMap.get(CacheKey.SPRING_CTX);
	}

	@PostConstruct
	public void postConstruct() throws Exception {
		contextMap.put(CacheKey.SPRING_CTX, appCtx);
	}

}
