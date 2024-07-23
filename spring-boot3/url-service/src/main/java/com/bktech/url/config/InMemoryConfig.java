package com.bktech.url.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import jakarta.annotation.PostConstruct;

public class InMemoryConfig {

	private String startupData;

	public InMemoryConfig(String startupData) {
		this.startupData = startupData;
	}

	@Autowired
	private DataSource datasource;

	@Autowired
	private ApplicationContext context;

	@PostConstruct
	public void loadIfInMemory() throws Exception {
		Resource resource = context.getResource(startupData);
		ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
	}
}