package com.bktech.user.security;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.bktech.user.constants.RoleType;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.security.type", havingValue = "JDBC")
public class JdbcAuthSecurityConfig {

	private final DataSource dataSource;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationEntryPoint authEntryPoint;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select user_name as username, user_pwd as password, 1 as enabled "
				+ " from users where user_name=?")
		.authoritiesByUsernameQuery("select u.user_name as username, r.name as authority "
				+ " from users u, roles r, user_roles ur where r.id=ur.role_id "
				+ " and u.id=ur.user_id and u.user_name=?")
		.passwordEncoder(passwordEncoder)
		;
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.requestMatchers("/api/user/**").hasAuthority(RoleType.USER.name())
		.requestMatchers("/api/admin/**").hasAuthority(RoleType.ADMIN.name())
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.httpBasic();
	}

}
