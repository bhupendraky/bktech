package com.techy.user.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techy.user.service.UserService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	UserService userService;
	

	/**
	 * Get the user details from user service
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
	
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
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/**")
		.permitAll()
		//.hasRole("ADMIN")
		.and()
		.formLogin();
	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}