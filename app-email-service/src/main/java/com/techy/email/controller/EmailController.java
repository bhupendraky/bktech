package com.techy.email.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/email")
public class EmailController {

	@GetMapping
	public String getEmail() {
		return "bhupendraky@gmail.com";
	}

	@PostMapping("/{email}")
	public String setEmail(String email) {
		return email;
	}
}
