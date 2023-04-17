package com.bktech.user.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.bktech.user.domain.Role;
import com.bktech.user.domain.UserEntity;
import com.bktech.user.dto.UserDTO;

public class UserMapper {

	public static UserEntity mapToUser(UserDTO userDto) {
		UserEntity user = new UserEntity();

		user.setEmail(userDto.getEmail());
		user.setEnabled(userDto.isEnabled());
		user.setPassword(userDto.getPassword());
		user.setUserName(userDto.getUserName());
		user.setAge(userDto.getAge());

		user.setAuditFields(userDto);

		if(!CollectionUtils.isEmpty(userDto.getRoles())) {
			Set<Role> roles = userDto.getRoles().stream()
					.map(Role::new)
					.collect(Collectors.toSet());
			user.setRoles(roles);
		}

		return user;
	}

	public static UserDTO mapToUserDTO(UserEntity user) {
		UserDTO userDto = new UserDTO();

		userDto.setEmail(user.getEmail());
		userDto.setEnabled(user.isEnabled());
		userDto.setPassword(user.getPassword());
		userDto.setUserName(user.getUserName());
		userDto.setAge(user.getAge());

		userDto.setAuditFields(user);

		if(!CollectionUtils.isEmpty(user.getRoles())) {
			Set<String> roles = user.getRoles().stream()
					.map(Role::getName)
					.collect(Collectors.toSet());
			userDto.setRoles(roles);
		}

		return userDto;
	}

}
