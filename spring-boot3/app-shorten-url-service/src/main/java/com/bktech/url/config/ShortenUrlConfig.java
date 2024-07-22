package com.bktech.url.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShortenUrlConfig {

	@Value("${spring.cloud.client.hostname}")
	private String hostName;

}
