package com.bktech.user.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.user.Traceble;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.service.UserService;
import com.bktech.user.vo.UserVO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

	private final UserService userService;

	@Traceble
	@ApiOperation("Register admin user")
	@PostMapping("/register-user")
	public UserVO registerUser(@Valid @RequestBody UserDTO dto) {
		return userService.registerUser(dto);
	}

	@Traceble
	@ApiOperation("Update a user")
	@PutMapping("/update-user")
	public UserVO updateUser(@Valid @RequestBody UserDTO dto) {
		return userService.updateUser(dto);
	}

	@Traceble
	@ApiOperation("Delete a user with the id")
	@DeleteMapping("/deregister-user/{username}")
	public String deleteUser(@PathVariable String username) {
		return userService.deleteUser(username);
	}

}
