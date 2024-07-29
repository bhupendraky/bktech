package com.bktech.gateway.validator;

import java.util.Base64;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.bktech.gateway.AuthConfig;
import com.bktech.infra.constants.Globals;

@Component
@ConditionalOnProperty(name = "infra.security.type", havingValue = "BASIC")
public class BasicAuthValidator implements AuthValidator {

	@Override
	public Optional<String> validate(ServerWebExchange exchange, GatewayFilterChain chain, AuthConfig config) {
		HttpHeaders headers = exchange.getRequest().getHeaders();
		if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
			throw new RuntimeException("Missing authorization header");
		}
		String authHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);
		if (StringUtils.isBlank(authHeader)) {
			throw new RuntimeException("Missing authorization header");
		}
		if (!authHeader.startsWith(Globals.BasicAuth.Prefix.TEXT)) {
			throw new RuntimeException("No a Basic token");
		}
		//TO-DO Basic auth
		String authStr = authHeader.substring(Globals.BasicAuth.Prefix.LENGTH);
		String pair = new String(Base64.getDecoder().decode(authStr));
		String username = pair.split(":")[0];
		//String password = pair.split(":")[1];
		return Optional.of(username);
	}


}
