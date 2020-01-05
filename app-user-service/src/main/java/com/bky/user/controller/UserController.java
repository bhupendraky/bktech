package com.bky.user.controller;

import java.util.List;

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

import com.bky.user.annotation.Traceble;
import com.bky.user.dto.UserDTO;
import com.bky.user.entity.User;
import com.bky.user.service.UserService;

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
	public List<User> getAllUserJpa() {
		return service.getAllUser();
	}

	@Traceble
	@ApiOperation("Fetch user details with the id")
	@GetMapping("/get/{id}")
	public User getUserJpa(@PathVariable Long id) {
		return service.getUser(id);
	}

	@Traceble
	@ApiOperation("Update a user")
	@PutMapping("/update")
	public User updateUserJpa(@RequestBody UserDTO dto) {
		return service.updateUser(dto);
	}

	@Traceble
	@ApiOperation("Add a user")
	@PostMapping("/add")
	public User addUserJpa(@RequestBody UserDTO dto) {
		return service.addUser(dto);
	}

	@Traceble
	@ApiOperation("Delete a user with the id")
	@DeleteMapping("/delete")
	public User deleteUserJpa(@PathVariable Long id) {
		return service.deleteUser(id);
	}
}
