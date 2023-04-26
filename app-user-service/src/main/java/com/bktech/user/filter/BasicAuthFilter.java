package com.bktech.user.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bktech.user.ctx.ExecutionContext;
import com.bktech.user.ctx.UserContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@ConditionalOnProperty(name = "spring.security.type", havingValue = "BASIC")
public class BasicAuthFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (auth == null) {
			filterChain.doFilter(request, response);
			return;
		}
		String pair = new String(Base64.getDecoder().decode(auth.substring(6)));
		String username = pair.split(":")[0];
		String password = pair.split(":")[1];
		UsernamePasswordAuthenticationToken newToken = new UsernamePasswordAuthenticationToken(username, password);
		newToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(newToken);
		try {
			ExecutionContext.getUserContext().set(new UserContext(username));
			// Delegate to next filter
			filterChain.doFilter(request, response);
		} finally {
			ExecutionContext.removeUserContext();
		}
	}

}
