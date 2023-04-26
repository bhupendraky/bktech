package com.bktech.user.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@ConditionalOnProperty(name = "spring.security.type", havingValue = "INMEMORY")
public class InMemorySecurityConfig {

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("BK")
		.password("BK")
		.roles("ADMIN");
	}

}
