package com.bktech.customer.ctx;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.bktech.customer.config.ErrorResourceConfig;

@Component
public class AppContext implements InitializingBean, DisposableBean {

	private static final Map<CacheKey, Object> ctx = new HashMap<>();

	private enum CacheKey {
		SPRING_CTX, SPRING_ENV;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ctx.put(CacheKey.SPRING_CTX, springCtx);
		ctx.put(CacheKey.SPRING_ENV, env);
	}

	@Autowired
	private ApplicationContext springCtx;

	@Autowired
	private Environment env;

	public ErrorResourceConfig getErrorConfig() {
		return getSpringCtx().getBean(ErrorResourceConfig.class);
	}

	public ApplicationContext getSpringCtx() {
		return (ApplicationContext) ctx.get(CacheKey.SPRING_CTX);
	}

	public Environment getEnv() {
		return (Environment) ctx.get(CacheKey.SPRING_ENV);
	}

	@Override
	public void destroy() throws Exception {
		ctx.clear();
	}

}
