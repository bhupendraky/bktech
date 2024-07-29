package com.bktech.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.bktech.infra.ctx.AppContext;
import com.bktech.infra.ctx.CacheKey;

import jakarta.annotation.PostConstruct;

public abstract class InfraApp {

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
