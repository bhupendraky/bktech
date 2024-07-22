package com.bktech.gateway;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;

public class AuthConfig {

	private static final List<String> openApiEndpoints = List.of(
			"/eureka",
			"/api/user/jwt/login"
			);

	public boolean isSecured(ServerHttpRequest request) {
		return openApiEndpoints.stream()
				.noneMatch(uri -> request.getURI().getPath().contains(uri));
	}
}
