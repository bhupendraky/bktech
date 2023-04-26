package com.bktech.fin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.fin.config.Traceble;
import com.bktech.fin.domain.UserAccount;
import com.bktech.fin.service.FincialService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/financial")
public class FinancialController {

	@Autowired
	private FincialService service;

	@Traceble
	@GetMapping("/get-all")
	public List<UserAccount> getAllAccounts() {
		return service.getAllAccounts();
	}

	@Traceble
	@GetMapping("/getUserId")
	public String getUserId() {
		return service.getUserId();
	}
}
