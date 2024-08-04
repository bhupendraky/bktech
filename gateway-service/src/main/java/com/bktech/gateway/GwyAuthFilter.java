package com.bktech.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.bktech.gateway.validator.AuthValidator;
import com.bktech.infra.constants.Globals;

@Component
public class GwyAuthFilter extends AbstractGatewayFilterFactory<Object> {

	@Value("${infra.openApiEndpoints:}")
	private List<String> openApiEndpoints;

	@Autowired
	private AuthValidator validator;

	public boolean isSecured(ServerHttpRequest request) {
		return openApiEndpoints.stream()
				.noneMatch(uri -> request.getURI().getPath().contains(uri));
	}

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			Optional<String> userId = Optional.empty();
			if (isSecured(exchange.getRequest())) {
				userId = validator.validate(exchange, chain);
			}
			userId.ifPresent(id -> exchange.getRequest().mutate().header(Globals.REQ_HEADER_USER_ID, id).build());
			// Delegate to next filter
			return chain.filter(exchange);
		};
	}

}
