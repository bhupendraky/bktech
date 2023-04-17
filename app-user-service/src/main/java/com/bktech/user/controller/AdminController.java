package com.bktech.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.user.Traceble;
import com.bktech.user.domain.Role;
import com.bktech.user.service.AdminService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Traceble
	@ApiOperation("Create a role")
	@PostMapping("/create/{role}")
	public Role createRole(@PathVariable String role) {
		return adminService.createRole(role);
	}
}
