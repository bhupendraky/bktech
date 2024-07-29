package com.bktech.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bktech.app.AbstractApp;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@ComponentScan({"com.bktech.infra", "com.bktech.app", "com.bktech.customer"})
@EnableFeignClients(basePackages = {"com.bktech.user.proxy"})
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class Application extends AbstractApp {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
