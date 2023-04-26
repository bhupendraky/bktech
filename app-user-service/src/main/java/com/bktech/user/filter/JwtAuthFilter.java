package com.bktech.user.filter;

import java.io.IOException;

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
import com.bktech.user.data.TokenRepository;
import com.bktech.user.execp.AppException;
import com.bktech.user.utils.JwtTokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.security.type", havingValue = "JWT")
public class JwtAuthFilter extends OncePerRequestFilter {

	private final TokenRepository tokenRepository;
	private final UserDetailsService userDetailsService;

	@Value("${spring.security.jwy.bearer-token}")
	private boolean bearerToken;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if((bearerToken && !StringUtils.startsWith(authHeader, "Bearer ")) ||
				SecurityContextHolder.getContext().getAuthentication() != null){
			filterChain.doFilter(request, response);
			return;
		}

		String token = authHeader.substring(7);
		// validate the token
		String username = JwtTokenUtil.getUsernameFromToken(token);
		boolean isValidState = tokenRepository.findByUserUsernameAndValueAndValid(username, token, true)
				.isPresent();
		if(!(isValidState && JwtTokenUtil.isValidToken(token, username))) {
			throw new AppException("Invalid authorization token");
		}
		// Set security context
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken newToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
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
