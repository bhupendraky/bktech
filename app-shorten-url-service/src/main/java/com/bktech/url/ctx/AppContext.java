package com.bktech.url.ctx;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.bktech.url.Application;
import com.bktech.url.config.ErrorResourceConfig;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class AppContext {

	private static final Map<CacheKey, Object> contextMap = new HashMap<>();

	@Autowired
	private Environment env;

	@PostConstruct
	public void postConstruct() throws Exception {
		contextMap.put(CacheKey.SPRING_ENV, env);
	}

	public ErrorResourceConfig getErrorConfig() {
		return Application.getSpringCtx().getBean(ErrorResourceConfig.class);
	}

	public Environment getEnv() {
		return (Environment) contextMap.get(CacheKey.SPRING_ENV);
	}

	@PreDestroy
	public void destroy() throws Exception {
		contextMap.clear();
	}

}
