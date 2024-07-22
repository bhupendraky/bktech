package com.bktech.user.config;

import java.text.MessageFormat;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:error-resource.properties")
public class ErrorResourceConfig {

	private final Environment env;

	public String getProperty(String key, Object... args) {
		return MessageFormat.format(env.getProperty(key), args);
	}

}

