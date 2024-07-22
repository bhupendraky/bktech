package com.bktech.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.bktech.user.data.TokenRepository;
import com.bktech.user.domain.Token;
import com.bktech.user.execp.AppException;
import com.bktech.user.execp.ExceptionCode;
import com.bktech.user.utils.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtLogoutService implements LogoutHandler {

	private final TokenRepository tokenRepository;

	@Value("${spring.security.jwy.bearer-token}")
	private boolean bearerToken;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (bearerToken && !StringUtils.startsWith(authHeader, "Bearer ")) {
			return;
		}
		String token = authHeader.substring(7);
		// invalidate the token
		String username = JwtTokenUtil.getUsernameFromToken(token);
		Token tokenEntity = tokenRepository.findByUserUsernameAndValueAndValid(username, token, true)
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0009, username, token));
		tokenEntity.setValid(false);
		tokenRepository.save(tokenEntity);
	}

}
