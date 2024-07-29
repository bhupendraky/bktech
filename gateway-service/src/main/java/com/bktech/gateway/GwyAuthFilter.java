package com.bktech.gateway;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.bktech.gateway.validator.AuthValidator;
import com.bktech.infra.constants.Globals;

@Component
public class GwyAuthFilter extends AbstractGatewayFilterFactory<AuthConfig> {

	@Autowired
	private AuthValidator validator;

	public GwyAuthFilter() {
		super(AuthConfig.class);
	}

	@Override
	public GatewayFilter apply(AuthConfig config) {
		return (exchange, chain) -> {
			Optional<String> userId = Optional.empty();
			if (config.isSecured(exchange.getRequest())) {
				userId = validator.validate(exchange, chain, config);
			}
			userId.ifPresent(id -> exchange.getRequest().mutate().header(Globals.REQ_HEADER_USER_ID, id).build());
			// Delegate to next filter
			return chain.filter(exchange);
		};
	}

}
