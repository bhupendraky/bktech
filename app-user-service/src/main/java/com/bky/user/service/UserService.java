package com.bky.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bky.user.data.UserJpaRepository;
import com.bky.user.domain.User;
import com.bky.user.dto.UserDTO;

@Service
public class UserService {


	@Autowired
	private UserJpaRepository repo;

	public User getUser(Long id) {
		return repo.findById(id).orElse(null);
	}

	public User updateUser(UserDTO dto) {
		User user = getUser(dto.getId());
		user.setName(dto.getName());
		return repo.save(user);
	}

	public List<User> getAllUser() {
		return repo.findAll();
	}

	public User deleteUser(Long id) {
		User user = repo.findById(id).orElse(null);
		repo.deleteById(id);
		return user;
	}

	public User addUser(UserDTO dto) {
		return repo.save(new User(dto.getName()));
	}
}
