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
import com.bky.user.config.UserDetailConfig.UserDetail;
import com.bky.user.dto.UserDTO;
import com.bky.user.entity.User;
import com.bky.user.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@Traceble
	@GetMapping("/rakesh")
	public UserDetail getRakeshConfig() {
		return service.getRakeshConfig();
	}

	@Traceble
	@GetMapping("/bhupen")
	public UserDetail getBhupenConfig() {
		return service.getBhupenConfig();
	}


	@Traceble
	@GetMapping("/get/jdbc")
	public List<User> getAllPersonJdbc() {
		return service.getAllPersonJdbc();
	}

	@Traceble
	@GetMapping("/get/em")
	public List<User> getAllPersonEm() {
		return service.getAllPersonEm();
	}

	@Traceble
	@GetMapping("/get/jpa")
	public List<User> getAllPersonJpa() {
		return service.getAllPersonJpa();
	}

	@Traceble
	@GetMapping("/get/jdbc/{id}")
	public User getPersonJdbc(@PathVariable Long id) {
		return service.getPersonJdbc(id);
	}

	@Traceble
	@GetMapping("/get/em/{id}")
	public User getPersonEm(@PathVariable Long id) {
		return service.getPersonEm(id);
	}

	@Traceble
	@GetMapping("/get/jpa/{id}")
	public User getPersonJpa(@PathVariable Long id) {
		return service.getPersonJpa(id);
	}


	@PutMapping("/update/jdbc")
	public User updatePersionJdbc(@RequestBody UserDTO dto) {
		return service.updatePersionJdbc(dto);
	}

	@PutMapping("/update/em")
	public User updatePersionEm(@RequestBody UserDTO dto) {
		return service.updatePersionEm(dto);
	}

	@PutMapping("/update/jpa")
	public User updatePersionJpa(@RequestBody UserDTO dto) {
		return service.updatePersionJpa(dto);
	}

	@PostMapping("/add/jdbc")
	public User addPersionJdbc(@RequestBody UserDTO dto) {
		return service.addPersionJdbc(dto);
	}

	@PostMapping("/add/em")
	public User addPersionEm(@RequestBody UserDTO dto) {
		return service.addPersionEm(dto);
	}

	@PostMapping("/add/jpa")
	public User addPersionJpa(@RequestBody UserDTO dto) {
		return service.addPersionJpa(dto);
	}

	@DeleteMapping ("/delete/jdbc/{id}")
	public User deletePersionJdbc(@PathVariable Long id) {
		return service.deletePersionJdbc(id);
	}

	@DeleteMapping("/delete/em/{id}")
	public User deletePersionEm(@PathVariable Long id) {
		return service.deletePersionEm(id);
	}

	@DeleteMapping("/delete/jpa/{id}")
	public User deletePersionJpa(@PathVariable Long id) {
		return service.deletePersionJpa(id);
	}
}
