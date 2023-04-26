package com.bktech.fin.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@ConditionalOnProperty(name = "spring.security.type", havingValue = "NONE")
public class NoAuthSecurityConfiguration {

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.anyRequest()
		.permitAll();
	}

}