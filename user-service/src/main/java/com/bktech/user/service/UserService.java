package com.bktech.user.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bktech.infra.constants.Globals;
import com.bktech.infra.execp.AppException;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.entity.UserEntity;
import com.bktech.user.execp.ExceptionCode;
import com.bktech.user.repository.UserRepository;
import com.bktech.user.utils.UserMapper;
import com.bktech.user.vo.UserVO;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserVO getUser(String userName) {
		return getUserEntity(userName)
				.map(UserMapper::mapToUserVO)
				.orElse(null);
	}

	public Optional<UserEntity> getUserEntity(String username) {
		var userEntity = userRepository.findByUsername(username)
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0005, username));
		return Optional.of(userEntity);
	}

	@Transactional
	public UserVO updateUser(UserDTO dto) {
		var savedUser = userRepository.findByUsername(dto.getUsername())
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0005, dto.getUsername()));
		var updateUser = UserMapper.mapValues(savedUser, dto);
		savedUser = userRepository.save(updateUser);
		return UserMapper.mapToUserVO(savedUser);
	}

	public List<UserVO> getAllUser() {
		return userRepository.findAll()
				.stream()
				.map(UserMapper::mapToUserVO)
				.collect(Collectors.toList());
	}

	@Transactional
	public String deleteUser(String username) {
		if (!userRepository.existsByUsername(username)) {
			throw new AppException(ExceptionCode.USRSVC_0005, username);
		}
		userRepository.deleteByUsername(username);
		return Globals.SUCCESS;
	}

	@Transactional
	public UserVO registerUser(UserDTO dto) {
		if (userRepository.existsByUsername(dto.getUsername())) {
			throw new AppException(ExceptionCode.USRSVC_0007, dto.getUsername());
		}
		var user = UserMapper.mapToUser(dto);
		user.setRole(dto.getRole());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		return UserMapper.mapToUserVO(user);
	}

	public Map<Integer, List<UserVO>> getUsersGroupedByAge() {
		return userRepository.findAll()
				.stream()
				.collect(Collectors.groupingBy(
						UserEntity::getAge,
						LinkedHashMap::new,
						Collectors.mapping(UserMapper::mapToUserVO, Collectors.toList()))
						);
	}

}
