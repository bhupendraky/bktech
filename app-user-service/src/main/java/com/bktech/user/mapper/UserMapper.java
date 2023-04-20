package com.bktech.user.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.bktech.user.domain.Role;
import com.bktech.user.domain.UserEntity;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.vo.UserVO;

public class UserMapper {

	public static UserEntity mapToUser(UserDTO userDto) {
		UserEntity user = new UserEntity();

		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		user.setAge(userDto.getAge());

		if(!CollectionUtils.isEmpty(userDto.getRoles())) {
			Set<Role> roles = userDto.getRoles().stream()
					.map(Role::new)
					.collect(Collectors.toSet());
			user.setRoles(roles);
		}

		return user;
	}

	public static UserVO mapToUserVO(UserEntity user) {
		UserVO userVO = new UserVO();

		userVO.setEmail(user.getEmail());
		userVO.setPassword(user.getPassword());
		userVO.setUsername(user.getUsername());
		userVO.setAge(user.getAge());
		userVO.setEnabled(user.isEnabled());
		userVO.setAccountNonExpired(user.isAccountNonExpired());
		userVO.setAccountNonLocked(user.isAccountNonLocked());
		userVO.setCredentialsNonExpired(user.isCredentialsNonExpired());

		userVO.setAuditFields(user);

		if(!CollectionUtils.isEmpty(user.getRoles())) {
			Set<String> roles = user.getRoles().stream()
					.map(Role::getName)
					.collect(Collectors.toSet());
			userVO.setRoles(roles);
		}

		return userVO;
	}

}
