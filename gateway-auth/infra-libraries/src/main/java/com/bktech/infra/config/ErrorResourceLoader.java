package com.bktech.infra.config;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:error-resource.properties")
public class ErrorResourceLoader {

	@Autowired
	private Environment env;

	public String getProperty(String key, Object... args) {
		return Optional.ofNullable(env.getProperty(key))
				.map(errMsg -> MessageFormat.format(errMsg, args))
				.orElse(key);
	}

}
