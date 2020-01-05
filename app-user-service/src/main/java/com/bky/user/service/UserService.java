package com.bky.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bky.user.config.UserDetailConfig;
import com.bky.user.config.UserDetailConfig.UserDetail;
import com.bky.user.data.UserEntityManager;
import com.bky.user.data.UserJdbcDao;
import com.bky.user.data.UserJpaRepository;
import com.bky.user.dto.UserDTO;
import com.bky.user.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDetailConfig userDetailConfig;

	@Autowired
	private UserJdbcDao jdbc;

	@Autowired
	private UserEntityManager manager;

	@Autowired
	private UserJpaRepository repo;

	public UserDetail getRakeshConfig() {
		return userDetailConfig.getRakesh();
	}

	public UserDetail getBhupenConfig() {
		return userDetailConfig.getBhupen();
	}

	public User getPersonJdbc(Long id) {
		return jdbc.findById(id);
	}

	public User getPersonEm(Long id) {
		return manager.findById(id);
	}

	public User getPersonJpa(Long id) {
		return repo.findById(id).orElse(null);
	}

	public User updatePersionJdbc(UserDTO dto) {
		User person = getPersonJdbc(dto.getId());
		person.setName(dto.getName());
		jdbc.update(person);
		return person;
	}

	public User updatePersionEm(UserDTO dto) {
		User person = getPersonJdbc(dto.getId());
		person.setName(dto.getName());
		return manager.save(person);
	}

	public User updatePersionJpa(UserDTO dto) {
		User person = getPersonJdbc(dto.getId());
		person.setName(dto.getName());
		return repo.save(person);
	}

	public List<User> getAllPersonJdbc() {
		return jdbc.findAll();
	}

	public List<User> getAllPersonEm() {
		return manager.findAll();
	}

	public List<User> getAllPersonJpa() {
		return repo.findAll();
	}

	public User deletePersionJdbc(Long id) {
		User person = jdbc.findById(id);
		jdbc.deleteById(id);
		return person;
	}

	public User deletePersionEm(Long id) {
		return manager.deleteById(id);
	}

	public User deletePersionJpa(Long id) {
		User person = jdbc.findById(id);
		repo.deleteById(id);
		return person;
	}

	public User addPersionJdbc(UserDTO dto) {
		User person = new User(dto.getId(), dto.getName());
		jdbc.insert(person);
		return person;
	}

	public User addPersionEm(UserDTO dto) {
		return manager.save(new User(dto.getName()));
	}

	public User addPersionJpa(UserDTO dto) {
		return repo.save(new User(dto.getName()));
	}
}
