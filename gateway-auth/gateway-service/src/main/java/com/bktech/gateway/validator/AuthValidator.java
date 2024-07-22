package com.bktech.gateway.validator;

import java.util.Optional;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;

import com.bktech.gateway.AuthConfig;

public interface AuthValidator {

	Optional<String> validate(ServerWebExchange exchange, GatewayFilterChain chain, AuthConfig config);

}
