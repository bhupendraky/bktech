package com.bktech.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"com.bktech.infra", "com.bktech.gateway"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}