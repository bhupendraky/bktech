package com.bktech.user.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.bktech.infra.constants.Globals;
import com.bktech.infra.execp.AppException;
import com.bktech.infra.utils.JwtTokenUtil;
import com.bktech.user.entity.Token;
import com.bktech.user.execp.ExceptionCode;
import com.bktech.user.repository.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtLogoutService implements LogoutHandler {

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(!StringUtils.startsWith(authHeader, Globals.JwtAuth.Prefix.TEXT)){
			return;
		}
		String token = authHeader.substring(Globals.JwtAuth.Prefix.LENGTH);
		// invalidate the token
		String username = JwtTokenUtil.getUsernameFromToken(token);
		Token tokenEntity = tokenRepository.findByUserUsernameAndValueAndValid(username, token, true)
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0009, username, token));
		tokenEntity.setValid(false);
		tokenRepository.save(tokenEntity);
	}

}
