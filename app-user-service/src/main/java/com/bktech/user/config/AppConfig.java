package com.bktech.user.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bktech.user.constants.Constants;
import com.bktech.user.ctx.ExecutionContext;
import com.bktech.user.ctx.UserContext;
import com.bktech.user.data.UserRepository;
import com.bktech.user.execp.AppException;
import com.bktech.user.execp.ExceptionCode;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

	private final UserRepository userRepository;

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
	public UserDetailsService userDetailsService() {
		return username -> userRepository
				.findByUsername(username)
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0005, username));
	}

}
