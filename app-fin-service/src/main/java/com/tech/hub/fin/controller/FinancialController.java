package com.tech.hub.fin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.hub.common.annotation.Traceble;
import com.tech.hub.fin.domain.UserAccount;
import com.tech.hub.fin.service.FincialService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/financial")
public class FinancialController {

	@Autowired
	private FincialService service;

	@Traceble
	@ApiOperation("Fetch all user account")
	@GetMapping("/get-all")
	public List<UserAccount> getAllAccounts() {
		return service.getAllAccounts();
	}

	@Traceble
	@ApiOperation("Get User ID from user proxy")
	@GetMapping("/getUserId")
	public String getUserId() {
		return service.getUserId();
	}
}
