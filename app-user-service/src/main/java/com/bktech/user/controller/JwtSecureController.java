package com.bktech.user.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.user.config.Traceble;
import com.bktech.user.dto.LoginDTO;
import com.bktech.user.service.JwtTokenService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt/user")
@ConditionalOnProperty(name = "spring.security.type", havingValue = "JWT")
public class JwtSecureController {

	private final JwtTokenService jwtTokenService;

	@Traceble
	@PostMapping("/login")
	public String login (@Validated @RequestBody LoginDTO loginDto) {
		return jwtTokenService.generateToken(loginDto);
	}

}
