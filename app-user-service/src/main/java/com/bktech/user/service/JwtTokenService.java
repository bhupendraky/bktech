package com.bktech.user.service;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.bktech.user.data.TokenRepository;
import com.bktech.user.domain.Token;
import com.bktech.user.domain.UserEntity;
import com.bktech.user.dto.LoginDTO;
import com.bktech.user.execp.AppException;
import com.bktech.user.security.JwtTokenHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.security.type", havingValue = "JWT")
public class JwtTokenService {

	private final UserService userService;
	private final TokenRepository tokenRepository;
	private final JwtTokenHelper helper;
	private final AuthenticationManager authenticationManager;

	public String generateToken(LoginDTO loginDto) {

		this.authenticate(loginDto.getUsername(), loginDto.getPassword());
		String token = helper.generateToken(loginDto.getUsername());

		Optional<UserEntity> userEntity = userService.getUserEntity(loginDto.getUsername());
		Token tokenEntity = userEntity.map(UserEntity::getToken).orElse(new Token());
		tokenEntity.setValid(true);
		tokenEntity.setValue(token);
		tokenEntity.setUser(userEntity.get());
		tokenRepository.save(tokenEntity);

		return token;
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
