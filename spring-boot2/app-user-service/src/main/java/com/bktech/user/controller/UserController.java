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

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	@Traceble
	@ApiOperation("Fetch all user details")
	@GetMapping("/get-all")
	public List<UserVO> getAllUser() {
		return userService.getAllUser();
	}

	@Traceble
	@ApiOperation("Fetch user details with the userName")
	@GetMapping("/get/{userName}")
	public UserVO getUser(@PathVariable String username) {
		return userService.getUser(username);
	}

	@Traceble
	@ApiOperation("Fetch user details grouped by age")
	@GetMapping("/grouped-by-age")
	public Map<Integer, List<UserVO>> getUsersGroupedByAge() {
		return userService.getUsersGroupedByAge();
	}

}
