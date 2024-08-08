package com.bktech.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.infra.config.Traceable;
import com.bktech.user.dto.LoginDTO;
import com.bktech.user.service.AuthService;

@RestController
@RequestMapping("/api/auth/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Traceable
	@PostMapping("/jwt/login")
	public String jwtLogin(@Validated @RequestBody LoginDTO loginDto) {
		return authService.generateToken(loginDto);
	}

	@Traceable
	@GetMapping("/basic/login/{userId}")
	public String basicLogin(@PathVariable String userId) {
		return authService.getPassword(userId);
	}

	@Traceable
	@PostMapping("/jwt/logout")
	public String jwtLogout() {
		return null;
	}

}
