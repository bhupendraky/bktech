package com.bktech.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.bktech.infra.execp.AppException;
import com.bktech.infra.utils.JwtTokenUtil;
import com.bktech.user.dto.LoginDTO;
import com.bktech.user.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;

	public String generateToken(LoginDTO loginDto) {
		this.authenticate(loginDto.getUsername(), loginDto.getPassword());
		return JwtTokenUtil.generateToken(loginDto.getUsername());
	}

	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		try {
			authenticationManager.authenticate(token);
		} catch (AuthenticationException e) {
			throw new AppException(e.getMessage());
		}
	}

	public String getPassword(String userId) {
		return userRepository.getPasswordByUsername(userId);
	}
}
