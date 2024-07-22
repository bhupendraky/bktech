package com.bktech.url.config;

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

import com.bktech.url.Constants;
import com.bktech.url.ctx.ExecutionContext;
import com.bktech.url.ctx.UserContext;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean("auditorAwareImpl")
    AuditorAware<String> auditorAwareImpl() {
		return () -> {
			String userId = Optional.ofNullable(ExecutionContext.getUserContext().get())
					.map(UserContext::getUserId)
					.orElse(Constants.APP_DOMAIN);
			return Optional.of(userId);
		};
	}

    @Bean
    RequestInterceptor requestInterceptor() {
		return template -> {
			Optional.ofNullable(ExecutionContext.getUserContext().get())
			.ifPresent(ctx -> template.header(Constants.REQ_HEADER_USER_ID, ctx.getUserId()));
		};
	}

    @Bean
    PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
		return (request, response, authentication) ->
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access denied");
	}

}
