package com.bktech.user.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.bktech.user.constants.RoleType;
import com.bktech.user.filter.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.security.type", havingValue = "JWT")
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthenticationEntryPoint authEntryPoint;
	private final JwtAuthFilter authFilter;
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final LogoutHandler logoutHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/api/jwt/user/login").permitAll()
		.antMatchers("/api/user/**").hasAuthority(RoleType.USER.name())
		.antMatchers("/api/admin/**").hasAuthority(RoleType.ADMIN.name())
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider())
		.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
		.logout()
		.logoutUrl("/api/jwt/user/logout")
		.addLogoutHandler(logoutHandler)
		.logoutSuccessHandler(
				(request, response, authentication) ->
				SecurityContextHolder.clearContext()
				);
	}

    @Bean
    AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

}
