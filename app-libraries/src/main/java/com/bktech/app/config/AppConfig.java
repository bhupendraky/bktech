package com.bktech.app.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import com.bktech.infra.constants.Globals;
import com.bktech.infra.ctx.ExecutionContext;
import com.bktech.infra.ctx.ExecutionData;

@Configuration
public class AppConfig {

	@Bean("auditorAwareImpl")
	AuditorAware<String> auditorAwareImpl() {
		return () -> {
			var auditor = Optional.ofNullable(ExecutionContext.getAuditContext().get())
					.map(ExecutionData::getUserId)
					.orElse(Globals.APP_DOMAIN);
			return Optional.of(auditor);
		};
	}

}
