package com.bktech.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.common.annotation.Traceble;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@Traceble
	@ApiOperation("Fetch all user details")
	@GetMapping("/get-all")
	public List<UserDTO> getAllUser() {
		return service.getAllUser();
	}

	@Traceble
	@ApiOperation("Fetch user details with the id")
	@GetMapping("/get/{id}")
	public UserDTO getUser(@PathVariable String id) {
		return service.getUser(id);
	}

	@Traceble
	@ApiOperation("Update a user")
	@PutMapping("/update")
	public UserDTO updateUser(@Valid @RequestBody UserDTO dto) {
		return service.updateUser(dto);
	}

	@Traceble
	@ApiOperation("Create a user")
	@PostMapping("/create")
	public UserDTO addUser(@Valid @RequestBody UserDTO dto) {
		return service.createUser(dto);
	}

	@Traceble
	@ApiOperation("Delete a user with the id")
	@DeleteMapping("/delete")
	public String deleteUser(@PathVariable String userName) {
		return service.deleteUser(userName);
	}

}
