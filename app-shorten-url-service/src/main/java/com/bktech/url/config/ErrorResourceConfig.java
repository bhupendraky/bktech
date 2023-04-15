package com.bktech.url.config;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:error-resource.properties")
public class ErrorResourceConfig {

	@Autowired
	private Environment env;

	public String getProperty(String key, Object... args) {
		return MessageFormat.format(env.getProperty(key), args);
	}

}

