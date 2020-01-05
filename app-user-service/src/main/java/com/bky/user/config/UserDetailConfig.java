package com.bky.user.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Configuration
public class UserDetailConfig {

	@Bean
	@ConfigurationProperties(prefix = "user.detail.bhupen")
	public UserDetail getBhupen() {
		return new UserDetail();
	}

	@Bean
	@ConfigurationProperties(prefix = "user.detail.rakesh")
	public UserDetail getRakesh() {
		return new UserDetail();
	}

	@Getter
	@Setter
	@ToString
	public class UserDetail {
		private String name;
		private String address;
		private Map<String, Integer> phone;
	}

}
