package com.tech.hub.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tech.hub.common.enums.Globals;
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

	public UserDTO getUser(String userName) {

		return userRepository.findByUserName(userName)
				.map(UserMapper::mapToUserDTO)
				.orElseThrow(() -> new UserServiceException(UserErrorCode.TS_01_0001));
	}

	public UserDTO updateUser(UserDTO dto) {
		User newUuser = UserMapper.mapToUser(dto);
		User savedUser = userRepository.save(newUuser);
		return UserMapper.mapToUserDTO(savedUser);
	}

	public List<UserDTO> getAllUser() {
		return userRepository.findAll()
				.stream()
				.map(UserMapper::mapToUserDTO)
				.collect(Collectors.toList());
	}

	public String deleteUser(String userName) {
		userRepository.deleteByUserName(userName);
		return Globals.SUCCESS.value();
	}

	public UserDTO createUser(UserDTO dto) {
		User newUser = UserMapper.mapToUser(dto);
		User savedUser = userRepository.save(newUser);
		return UserMapper.mapToUserDTO(savedUser);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return userRepository.findByUserName(userName).map(AppUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
	}
}
