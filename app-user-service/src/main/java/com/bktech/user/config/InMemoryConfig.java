package com.bktech.user.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InMemoryConfig {

	private final String startupData;

	private final DataSource datasource;
	private final ApplicationContext context;

	@PostConstruct
	public void loadIfInMemory() throws Exception {
		Resource resource = context.getResource(startupData);
		ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
	}
}