package com.bktech.user;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bktech.user.config.SwaggerPropertiesInitializer;
import com.bktech.user.ctx.AppContext;
import com.bktech.user.ctx.AuditorAwareImpl;
import com.bktech.user.ctx.RequestInterceptorImpl;
import com.spring4all.swagger.EnableSwagger2Doc;
import com.spring4all.swagger.SwaggerProperties;

import feign.RequestInterceptor;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@EnableJpaRepositories
@EnableFeignClients(basePackages = {"com.bktech.user.proxy"})
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class Application implements InitializingBean, DisposableBean {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private ApplicationContext appCtx;
	private static ThreadLocal<ApplicationContext> context = new ThreadLocal<>();

	public static AppContext getContext() {
		return context.get().getBean(AppContext.class);
	}

	@Autowired
	private SwaggerProperties swaggerProperties;

	@Override
	public void afterPropertiesSet() throws Exception {
		SwaggerPropertiesInitializer.configureSwaggerHeader(swaggerProperties);
		context.set(appCtx);
	}

	@Override
	public void destroy() throws Exception {
		context.remove();
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
