package com.bktech.user.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.bktech.infra.constants.Globals;
import com.bktech.infra.utils.JwtTokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtLogoutService implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(!StringUtils.startsWith(authHeader, Globals.JwtAuth.Prefix.TEXT)){
			return;
		}
		var token = authHeader.substring(Globals.JwtAuth.Prefix.LENGTH);
		// invalidate the token
		var username = JwtTokenUtil.getUsernameFromToken(token);
		System.out.println("Logout successfull for " + username);
	}

}
