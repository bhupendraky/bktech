package com.bktech.app.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import com.bktech.infra.constants.Globals;
import com.bktech.infra.ctx.AuditContext;
import com.bktech.infra.ctx.ExecutionContext;

import feign.RequestInterceptor;

@Configuration
public class AppConfig {

	//@Bean
	RequestInterceptor requestInterceptor() {
		return template -> {
			Optional.ofNullable(ExecutionContext.getAuditContext().get())
			.ifPresent(ctx -> template.header(Globals.REQ_HEADER_USER_ID, ctx.getAuditor()));
		};
	}

	@Bean("auditorAwareImpl")
	AuditorAware<String> auditorAwareImpl() {
		return () -> {
			String auditor = Optional.ofNullable(ExecutionContext.getAuditContext().get())
					.map(AuditContext::getAuditor)
					.orElse(Globals.APP_DOMAIN);
			return Optional.of(auditor);
		};
	}

}
