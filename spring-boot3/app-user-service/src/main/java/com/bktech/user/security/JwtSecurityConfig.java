package com.bktech.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.bktech.user.constants.RoleType;
import com.bktech.user.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(name = "spring.security.type", havingValue = "JWT")
public class JwtSecurityConfig {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	@Autowired
	private JwtAuthFilter authFilter;
	@Autowired
	private LogoutHandler logoutHandler;
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request
						.requestMatchers("/api/jwt/user/login").permitAll()
						.requestMatchers("/api/user/**").hasAuthority(RoleType.USER.name())
						.requestMatchers("/api/admin/**").hasAuthority(RoleType.ADMIN.name())
						.anyRequest().authenticated()
						)
				.exceptionHandling(eh -> eh.authenticationEntryPoint(authEntryPoint))
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(logout -> logout
						.logoutUrl("/api/jwt/user/logout")
						.addLogoutHandler(logoutHandler)
						.logoutSuccessHandler(
								(request, response, authentication) ->
								SecurityContextHolder.clearContext()
								))
				.build();
	}

}
