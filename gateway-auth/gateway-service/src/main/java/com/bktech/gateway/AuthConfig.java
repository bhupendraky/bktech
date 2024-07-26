package com.bktech.gateway;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;

public class AuthConfig {


	private static final List<String> openApiEndpoints = List.of(
			"/eureka",
			"/api/auth/user/jwt/login",
			"/api/auth/user/basic/login"
			);

	public boolean isSecured(ServerHttpRequest request) {
		return openApiEndpoints.stream()
				.noneMatch(uri -> request.getURI().getPath().contains(uri));
	}
}
