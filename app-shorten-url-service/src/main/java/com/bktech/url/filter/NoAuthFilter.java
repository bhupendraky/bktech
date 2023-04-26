package com.bktech.url.filter;

import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bktech.url.Constants;
import com.bktech.url.ctx.ExecutionContext;
import com.bktech.url.ctx.UserContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@ConditionalOnProperty(name = "spring.security.type", havingValue = "NONE")
public class NoAuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			ExecutionContext.getUserContext().set(new UserContext(Constants.APP_DOMAIN));
			filterChain.doFilter(request, response);
		} finally {
			ExecutionContext.removeUserContext();
		}
	}
}
