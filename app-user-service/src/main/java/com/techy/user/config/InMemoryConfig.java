package com.techy.user.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

@Configuration
public class InMemoryConfig {

	private final String STARTUP_DATA = "classpath:h2-in-memory-data.sql";

	@Autowired
	private DataSource datasource;

	@Autowired
	private ApplicationContext context;

	@PostConstruct
	public void loadIfInMemory() throws Exception {
		Resource resource = context.getResource(STARTUP_DATA);
		ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
	}
}