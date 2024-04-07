package com.bktech.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bktech.user.constants.RoleType;
import com.bktech.user.filter.BasicAuthFilter;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(name = "spring.security.type", havingValue = "BASIC")
public class RoleBasedBasicSecurityConfig {

	@Autowired
	private BasicAuthFilter authFilter;
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers("/api/user/**").hasAuthority(RoleType.USER.name())
						.requestMatchers("/api/admin/**").hasAuthority(RoleType.ADMIN.name())
						.anyRequest().authenticated())
				.exceptionHandling(eh -> eh.authenticationEntryPoint(authEntryPoint))
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.httpBasic(c -> {})
				.build();
	}

}