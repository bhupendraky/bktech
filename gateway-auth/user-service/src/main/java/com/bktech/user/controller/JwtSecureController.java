package com.bktech.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.infra.config.Traceble;
import com.bktech.user.dto.LoginDTO;
import com.bktech.user.service.JwtTokenService;

@RestController
@RequestMapping("/api/user/jwt")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JwtSecureController {

	@Autowired
	private JwtTokenService jwtTokenService;

	@Traceble
	@PostMapping("/login")
	public String login(@Validated @RequestBody LoginDTO loginDto) {
		return jwtTokenService.generateToken(loginDto);
	}

}
