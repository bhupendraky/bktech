package com.bktech.user.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.bktech.user.domain.Authority;
import com.bktech.user.domain.User;
import com.bktech.user.dto.AuthorityDTO;
import com.bktech.user.dto.UserDTO;

public class UserMapper {

	public static User mapToUser(UserDTO userDto) {
		User user = new User();

		user.setEmail(userDto.getEmail());
		user.setEnabled(userDto.isEnabled());
		user.setPassword(userDto.getPassword());
		user.setUserName(userDto.getUserName());

		user.setAuditFields(userDto);

		if(!CollectionUtils.isEmpty(userDto.getAuthorities())) {
			Set<Authority> auths = userDto.getAuthorities().stream()
					.map(authDto -> {
						Authority auth = new Authority();
						auth.setAuthority(authDto.getAuthority());
						auth.setUser(user);
						auth.setAuditFields(authDto);
						return auth;
					}).collect(Collectors.toSet());
			user.setAuthorities(auths);
		}

		return user;
	}

	public static UserDTO mapToUserDTO(User user) {
		UserDTO userDto = new UserDTO();

		userDto.setEmail(user.getEmail());
		userDto.setEnabled(user.isEnabled());
		userDto.setPassword(user.getPassword());
		userDto.setUserName(user.getUserName());

		userDto.setAuditFields(user);

		if(!CollectionUtils.isEmpty(user.getAuthorities())) {
			Set<AuthorityDTO> auths = user.getAuthorities().stream()
					.map(auth -> {
						AuthorityDTO authDto = new AuthorityDTO();
						authDto.setAuthority(auth.getAuthority());
						authDto.setAuditFields(auth);
						return authDto;
					}).collect(Collectors.toSet());
			userDto.setAuthorities(auths);
		}

		return userDto;
	}

}
