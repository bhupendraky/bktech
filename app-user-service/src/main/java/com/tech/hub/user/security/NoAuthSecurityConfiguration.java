package com.tech.hub.user.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Profile("dev")
@EnableWebSecurity
public class NoAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.anyRequest()
		.permitAll();
	}

	@Autowired
	private DataSource dataSource;

	/**
	 * Get the user details from data source
	 */
	protected void configure3(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select user_name as username, password, enabled "
				+ " from techy_user where user_name=?")
		.authoritiesByUsernameQuery("select user_name as username, authority "
				+ " from authorities where user_name=?");
	}

	/**
	 * Configure runtime user data in default schema provided by Spring
	 * @param auth
	 * @throws Exception
	 */
	protected void configure2(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.withDefaultSchema()
		.withUser(
				User.withUsername("bk")
				.password("bkp")
				.roles("ADMIN")
				).withUser(
						User.withUsername("rk")
						.password("rkp")
						.roles("USER")
						);
	}

	/**
	 * Configure in memory data at runtime
	 *
	 * @param auth
	 * @throws Exception
	 */
	protected void configure1(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("bk")
		.password("bkp")
		.roles("ADMIN");
	}

}