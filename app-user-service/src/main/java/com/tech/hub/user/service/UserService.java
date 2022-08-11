package com.tech.hub.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tech.hub.user.data.UserJpaRepository;
import com.tech.hub.user.domain.User;
import com.tech.hub.user.dto.AppUserDetails;
import com.tech.hub.user.dto.UserDTO;
import com.tech.hub.user.errors.UserErrorCode;
import com.tech.hub.user.errors.UserServiceException;
import com.tech.hub.user.mapper.UserMapper;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserJpaRepository userRepository;

	public User getUser(String userName) {

		return userRepository.findById(userName).orElseThrow(
				() -> new UserServiceException(UserErrorCode.TS_01_0001));
	}

	public User updateUser(UserDTO dto) {
		User user = UserMapper.getMapper().userDtoToUserMapper(dto);
		return userRepository.save(user);
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User deleteUser(String id) {
		User user = userRepository.findById(id).orElse(null);
		userRepository.deleteById(id);
		return user;
	}

	public User createUser(UserDTO dto) {
		User user = UserMapper.getMapper().userDtoToUserMapper(dto);
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return userRepository.findByUserName(userName)
				.map(AppUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
	}
}
