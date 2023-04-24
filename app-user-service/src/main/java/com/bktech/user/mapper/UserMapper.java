package com.bktech.user.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import com.bktech.user.Application;
import com.bktech.user.domain.Role;
import com.bktech.user.domain.UserEntity;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.vo.UserVO;

public class UserMapper {

	public static UserEntity mapToUser(UserDTO userDto) {
		return mapValues(new UserEntity(), userDto);
	}

	public static UserEntity mapValues(UserEntity target, UserDTO source) {
		target.setEmail(source.getEmail());
		PasswordEncoder passwordEncoder = Application.getSpringCtx().getBean(PasswordEncoder.class);
		target.setPassword(passwordEncoder.encode(source.getPassword()));
		target.setUsername(source.getUsername());
		target.setAge(source.getAge());

		if(!CollectionUtils.isEmpty(source.getRoles())) {
			Set<Role> roles = source.getRoles().stream()
					.map(Role::new)
					.collect(Collectors.toSet());
			target.getRoles().addAll(roles);
		}

		return target;
	}

	public static UserVO mapToUserVO(UserEntity user) {
		UserVO userVO = new UserVO();

		userVO.setEmail(user.getEmail());
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
