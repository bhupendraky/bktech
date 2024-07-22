package com.bktech.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bktech.app.security.AppUserDetails;
import com.bktech.infra.execp.AppException;
import com.bktech.user.execp.ExceptionCode;
import com.bktech.user.repository.UserRepository;

@Configuration
public class UserAppConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Bean
	UserDetailsService userDetailsService() {
		return username -> userRepository.findByUsername(username)
				.map(userVO -> {
					AppUserDetails userDetails = new AppUserDetails();
					userDetails.setUsername(username);
					userDetails.setPassword(userVO.getPassword());
					return userDetails;
				})
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0005, username));
	}

	@Bean
	AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

}
