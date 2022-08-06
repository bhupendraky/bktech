package com.techy.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techy.user.data.UserJpaRepository;
import com.techy.user.domain.User;
import com.techy.user.dto.AppUserDetails;
import com.techy.user.dto.UserDTO;
import com.techy.user.errors.UserErrorCode;
import com.techy.user.errors.UserServiceException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserJpaRepository repo;

	public User getUser(String userName) {

		return repo.findById(userName).orElseThrow(
				() -> new UserServiceException(UserErrorCode.TS_01_0001));
	}

	public User updateUser(UserDTO dto) {
		User user = getUser(dto.getUserName());
		user.setUserName(dto.getUserName());
		user.setPassword(dto.getPassword());
		user.setEnabled(dto.getEnabled());
		return repo.save(user);
	}

	public List<User> getAllUser() {
		return repo.findAll();
	}

	public User deleteUser(String id) {
		User user = repo.findById(id).orElse(null);
		repo.deleteById(id);
		return user;
	}

	public User addUser(UserDTO dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setPassword(dto.getPassword());
		user.setEnabled(dto.getEnabled());
		
		return repo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return new AppUserDetails(getUser(userName));
	}
}
