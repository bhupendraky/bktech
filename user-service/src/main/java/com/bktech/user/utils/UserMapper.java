package com.bktech.user.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.bktech.user.Application;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.entity.UserEntity;
import com.bktech.user.vo.UserVO;

public class UserMapper {

	public static UserEntity mapToUser(UserDTO userDto) {
		return mapValues(new UserEntity(), userDto);
	}

	public static UserEntity mapValues(UserEntity target, UserDTO source) {
		target.setEmail(source.getEmail());
		var passwordEncoder = Application.getSpringCtx().getBean(PasswordEncoder.class);
		target.setPassword(passwordEncoder.encode(source.getPassword()));
		target.setUsername(source.getUsername());
		target.setAge(source.getAge());
		target.setRole(source.getRole());

		return target;
	}

	public static UserVO mapToUserVO(UserEntity user) {
		var userVO = new UserVO();

		userVO.setEmail(user.getEmail());
		userVO.setUsername(user.getUsername());
		userVO.setAge(user.getAge());
		userVO.setRole(user.getRole());
		userVO.setAuditFields(user);
		return userVO;
	}

}
