package com.bktech.user.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bktech.user.ctx.ExecutionContext;
import com.bktech.user.ctx.UserContext;
import com.bktech.user.execp.AppException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.security.type", havingValue = "JWT")
public class JwtAuthFilter extends OncePerRequestFilter {

	private final UserDetailsService userDetailsService;
	private final JwtTokenHelper helper;

	@Value("${spring.security.jwy.bearer-token}")
	private boolean bearerToken;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if((!bearerToken || StringUtils.startsWith(token, "Bearer ")) &&
				SecurityContextHolder.getContext().getAuthentication() == null) {
			token = token.substring(7);
			String username = helper.getUsernameFromToken(token);
			if(username == null) {
				throw new AppException("Authentication details required");
			}
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if(!helper.isValidToken(token, username)) {
				throw new AppException("Invalid authorization token");
			}
			UsernamePasswordAuthenticationToken newToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities()
					);
			newToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(newToken);
			ExecutionContext.getUserContext().set(new UserContext(username));
		}
		filterChain.doFilter(request, response);
	}

}
