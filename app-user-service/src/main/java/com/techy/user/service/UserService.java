package com.techy.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techy.user.data.UserJpaRepository;
import com.techy.user.domain.User;
import com.techy.user.dto.UserDTO;
import com.techy.user.errors.UserErrorCode;
import com.techy.user.errors.UserServiceException;

@Service
public class UserService {

	@Autowired
	private UserJpaRepository repo;

	public User getUser(Long id) {

		return repo.findById(id).orElseThrow(
				() -> new UserServiceException(UserErrorCode.TS_01_0001));
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
