package com.bktech.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.user.config.Traceble;
import com.bktech.user.service.UserService;
import com.bktech.user.vo.UserVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	private final UserService userService;

	@Traceble
	@GetMapping("/get-all")
	public List<UserVO> getAllUser() {
		return userService.getAllUser();
	}

	@Traceble
	@GetMapping("/get/{username}")
	public UserVO getUserByUsername(@PathVariable String username) {
		return userService.getUser(username);
	}

	@Traceble
	@GetMapping("/grouped-by-age")
	public Map<Integer, List<UserVO>> getUsersGroupedByAge () {
		return userService.getUsersGroupedByAge();
	}

}
