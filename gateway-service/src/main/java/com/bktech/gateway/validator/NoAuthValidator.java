package com.bktech.gateway.validator;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.bktech.infra.constants.Globals;

@Component
@ConditionalOnProperty(name = "infra.security.type", havingValue = "NONE")
public class NoAuthValidator implements AuthValidator {

	@Override
	public Optional<String> validate(ServerWebExchange exchange, GatewayFilterChain chain) {
		return Optional.of(Globals.APP_DOMAIN);
	}

}
