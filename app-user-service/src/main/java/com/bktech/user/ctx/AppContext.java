package com.bktech.user.ctx;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.bktech.user.Application;
import com.bktech.user.config.ErrorResourceConfig;

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
