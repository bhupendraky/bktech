package com.bktech.infra.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.bktech.infra.constants.Globals;
import com.bktech.infra.ctx.ExecutionContext;

import feign.RequestInterceptor;

@Configuration
public class InfraConfig {

	@Bean
	RequestInterceptor requestInterceptor() {
		return template -> {
			Optional.ofNullable(ExecutionContext.getAuditContext().get())
			.ifPresent(ctx -> {
				template.header(Globals.REQ_HEADER_USER_ID,
						Optional.ofNullable(ctx.getUserId()).orElse(Globals.APP_DOMAIN));
				template.header(HttpHeaders.AUTHORIZATION, ctx.getAuthHeader());
			});
		};
	}

}
