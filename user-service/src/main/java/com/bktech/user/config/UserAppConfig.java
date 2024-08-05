package com.bktech.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.bktech.infra.execp.AppException;
import com.bktech.user.execp.ExceptionCode;
import com.bktech.user.repository.UserRepository;
import com.bktech.user.security.UserDetailsImpl;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class UserAppConfig {

	@Autowired
	private UserRepository userRepository;

	@Bean
	UserDetailsService userDetailsService() {
		return username -> userRepository.findByUsername(username)
				.map(userVO -> {
					var userDetails = new UserDetailsImpl();
					userDetails.setUsername(username);
					userDetails.setPassword(userVO.getPassword());
					return userDetails;
				})
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0005, username));
	}

	@Bean
	AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
		var authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}


	@Bean
	PasswordEncoder passwordEncoder() {
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
