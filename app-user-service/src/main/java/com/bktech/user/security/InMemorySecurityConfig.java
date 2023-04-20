package com.bktech.user.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@ConditionalOnProperty(name = "spring.security.type", havingValue = "IN-MEMORY")
public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Configure in memory data at runtime
	 *
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("BK")
		.password("BK")
		.roles("ADMIN");
	}

}
