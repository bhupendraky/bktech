package com.bktech.gateway.validator;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.bktech.infra.constants.Globals;
import com.bktech.infra.utils.JwtTokenUtil;

@Component
@ConditionalOnProperty(name = "infra.security.type", havingValue = "JWT")
public class JwtAuthValidator implements AuthValidator {

	@Override
	public Optional<String> validate(ServerWebExchange exchange, GatewayFilterChain chain) {
		var headers = exchange.getRequest().getHeaders();
		if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
			throw new RuntimeException("Missing authorization header");
		}
		var authHeader = Objects.requireNonNull(headers.get(HttpHeaders.AUTHORIZATION)).get(0);
		if (StringUtils.isBlank(authHeader)) {
			throw new RuntimeException("Missing authorization header");
		}
		if (!authHeader.startsWith(Globals.JwtAuth.Prefix.TEXT)) {
			throw new RuntimeException("No a Bearer tocken");
		}
		var token = authHeader.substring(Globals.JwtAuth.Prefix.LENGTH);
		var userId = JwtTokenUtil.getUsernameFromToken(token);
		return Optional.of(userId);
	}

}
