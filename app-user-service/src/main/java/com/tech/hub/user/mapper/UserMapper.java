package com.tech.hub.user.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.tech.hub.common.domain.AuditableDTO;
import com.tech.hub.common.domain.AuditableEntity;
import com.tech.hub.user.domain.Authority;
import com.tech.hub.user.domain.User;
import com.tech.hub.user.dto.AuthorityDTO;
import com.tech.hub.user.dto.UserDTO;

public class UserMapper {

	public static User mapToUser(UserDTO userDto) {
		User user = new User();

		user.setEmail(userDto.getEmail());
		user.setEnabled(userDto.isEnabled());
		user.setPassword(userDto.getPassword());
		user.setUserName(userDto.getUserName());

		setAudits(user, userDto);

		if(!CollectionUtils.isEmpty(userDto.getAuthorities())) {
			Set<Authority> auths = userDto.getAuthorities().stream()
					.map(authDto -> {
						Authority auth = new Authority();
						auth.setAuthority(authDto.getAuthority());
						auth.setUser(user);
						setAudits(auth, authDto);
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

		setAudits(userDto, user);

		if(!CollectionUtils.isEmpty(user.getAuthorities())) {
			Set<AuthorityDTO> auths = user.getAuthorities().stream()
					.map(auth -> {
						AuthorityDTO authDto = new AuthorityDTO();
						authDto.setAuthority(auth.getAuthority());
						setAudits(authDto, auth);
						return authDto;
					}).collect(Collectors.toSet());
			userDto.setAuthorities(auths);
		}

		return userDto;
	}

	private static <T>void setAudits(AuditableEntity<T> entity, AuditableDTO<T> dto) {
		entity.setCreatedBy(dto.getCreatedBy());
		entity.setCreatedOn(dto.getCreatedOn());
		entity.setUpdatedBy(dto.getUpdatedBy());
		entity.setUpdatedOn(dto.getUpdatedOn());
	}


	private static <T> void setAudits(AuditableDTO<T> dto, AuditableEntity<T> entity) {
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedOn(entity.getCreatedOn());
		dto.setUpdatedBy(entity.getUpdatedBy());
		dto.setUpdatedOn(entity.getUpdatedOn());
	}


}
