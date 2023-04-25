package com.bktech.customer.config;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.bktech.customer.Constants;
import com.bktech.customer.ctx.ExecutionContext;
import com.bktech.customer.ctx.UserContext;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

	@Bean("auditorAwareImpl")
	public AuditorAware<String> auditorAwareImpl() {
		return () -> {
			String userId = Optional.ofNullable(ExecutionContext.getUserContext().get())
					.map(UserContext::getUserId)
					.orElse(Constants.APP_DOMAIN);
			return Optional.of(userId);
		};
	}

	@Bean
	public RequestInterceptor requestInterceptor() {
		return template -> {
			Optional.ofNullable(ExecutionContext.getUserContext().get())
			.ifPresent(ctx -> template.header(Constants.REQ_HEADER_USER_ID, ctx.getUserId()));
		};
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return (request, response, authentication) ->
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access denied");
	}

}
