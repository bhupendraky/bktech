package com.bktech.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bktech.infra.config.Traceable;
import com.bktech.user.service.UserService;
import com.bktech.user.vo.UserVO;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@Traceable
	@GetMapping("/get-all")
	public List<UserVO> getAllUser() {
		return userService.getAllUser();
	}

	@Traceable
	@GetMapping("/get/{username}")
	public UserVO getUserByUsername(@PathVariable String username) {
		return userService.getUser(username);
	}

	@Traceable
	@GetMapping("/grouped-by-age")
	public Map<Integer, List<UserVO>> getUsersGroupedByAge() {
		return userService.getUsersGroupedByAge();
	}

}
