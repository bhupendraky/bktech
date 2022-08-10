package com.techy.user.mapper;

import com.techy.user.domain.User;
import com.techy.user.dto.UserDTO;

//@Mapper
public class UserMapper {

	private UserMapper() {
	}

	private static UserMapper instance = new UserMapper();

	public static UserMapper getMapper() {
		return instance;
	}

	public User userDtoToUserMapper(UserDTO dto) {
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setEnabled(dto.getEnabled());
		user.setPassword(dto.getPassword());
		user.setUserName(dto.getUserName());
		return user;
	}

	public UserDTO userToUserDtoMapper(User user) {
		UserDTO dto = new UserDTO();
		dto.setEmail(user.getEmail());
		dto.setEnabled(user.getEnabled());
		dto.setPassword(user.getPassword());
		dto.setUserName(user.getUserName());
		return dto;
	}
}
