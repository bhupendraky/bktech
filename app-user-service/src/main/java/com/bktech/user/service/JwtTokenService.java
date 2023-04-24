package com.bktech.user.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.bktech.user.dto.LoginDTO;
import com.bktech.user.execp.AppException;
import com.bktech.user.security.JwtTokenHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.security.type", havingValue = "JWT")
public class JwtTokenService {

	private final JwtTokenHelper helper;
	private final AuthenticationManager authenticationManager;

	public String generateToken(LoginDTO loginDto) {
		this.authenticate(loginDto.getUsername(), loginDto.getPassword());
		return helper.generateToken(loginDto.getUsername());
	}

	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		try {
			authenticationManager.authenticate(token);
		} catch (AuthenticationException e) {
			throw new AppException(e.getMessage());
		}
	}
}
