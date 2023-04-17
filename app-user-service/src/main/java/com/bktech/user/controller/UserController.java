package com.bktech.user.controller;

import java.util.List;
import java.util.Map;

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

import com.bktech.user.Traceble;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Traceble
	@ApiOperation("Fetch all user details")
	@GetMapping("/get-all")
	public List<UserDTO> getAllUser() {
		return userService.getAllUser();
	}

	@Traceble
	@ApiOperation("Fetch user details with the userName")
	@GetMapping("/get/{userName}")
	public UserDTO getUser(@PathVariable String userName) {
		return userService.getUser(userName);
	}

	@Traceble
	@ApiOperation("Update a user")
	@PutMapping("/update")
	public UserDTO updateUser(@Valid @RequestBody UserDTO dto) {
		return userService.updateUser(dto);
	}

	@Traceble
	@ApiOperation("Register a user")
	@PostMapping("/register")
	public UserDTO registerUser(@Valid @RequestBody UserDTO dto) {
		return userService.registerUser(dto);
	}

	@Traceble
	@ApiOperation("Delete a user with the id")
	@DeleteMapping("/delete")
	public String deleteUser(@PathVariable String userName) {
		return userService.deleteUser(userName);
	}

	@Traceble
	@ApiOperation("Fetch user details grouped by age")
	@GetMapping("/grouped-by-age")
	public Map<Integer, List<UserDTO>> getUsersGroupedByAge () {
		return userService.getUsersGroupedByAge();
	}

}
