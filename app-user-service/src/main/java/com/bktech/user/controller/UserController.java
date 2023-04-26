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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	@Traceble
	@GetMapping("/get-all")
	public List<UserVO> getAllUser() {
		return userService.getAllUser();
	}

	@Traceble
	@GetMapping("/get/{userName}")
	public UserVO getUser(@PathVariable String username) {
		return userService.getUser(username);
	}

	@Traceble
	@GetMapping("/grouped-by-age")
	public Map<Integer, List<UserVO>> getUsersGroupedByAge () {
		return userService.getUsersGroupedByAge();
	}

}
