package com.bktech.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class WebClientConfig implements ApplicationRunner {

	@Autowired
	private WebClient.Builder webClientBuilder;

	private WebClient userServiceClient;

	public WebClient getUserServiceClient() {
		return userServiceClient;
	}

	public WebClient buildWebClient(String url) {
		return webClientBuilder.baseUrl(url).build();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.userServiceClient = this.buildWebClient("http://localhost:55811");
	}

	public Mono<String> getUserPassword(String userId) {
		return this.userServiceClient
				.get()
				.uri("/api/auth/user/basic/login/{userId}", userId)
				.retrieve()
				.bodyToMono(String.class);
	}

}
