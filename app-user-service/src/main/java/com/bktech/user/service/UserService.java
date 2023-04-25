package com.bktech.user.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bktech.user.constants.Constants;
import com.bktech.user.constants.RoleType;
import com.bktech.user.data.RoleRepository;
import com.bktech.user.data.UserRepository;
import com.bktech.user.domain.Role;
import com.bktech.user.domain.UserEntity;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.execp.AppException;
import com.bktech.user.execp.ExceptionCode;
import com.bktech.user.utils.UserMapper;
import com.bktech.user.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserVO getUser(String userName) {
		return getUserEntity(userName)
				.map(UserMapper::mapToUserVO)
				.orElse(null);
	}

	public Optional<UserEntity> getUserEntity(String username) {
		UserEntity userEntity = userRepository.findByUsername(username)
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0005, username));
		return Optional.of(userEntity);
	}

	@Transactional
	public UserVO updateUser(UserDTO dto) {
		UserEntity savedUser = userRepository.findByUsername(dto.getUsername())
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0005, dto.getUsername()));
		UserEntity updateUser = UserMapper.mapValues(savedUser, dto);
		savedUser= userRepository.save(updateUser);
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
		if(!userRepository.existsByUsername(username)) {
			throw new AppException(ExceptionCode.USRSVC_0005, username);
		}
		userRepository.deleteByUsername(username);
		return Constants.SUCCESS;
	}

	@Transactional
	public UserVO registerUser(UserDTO dto) {
		if(userRepository.existsByUsername(dto.getUsername())) {
			throw new AppException(ExceptionCode.USRSVC_0007, dto.getUsername());
		}
		Set<Role> roles = null;
		if(!CollectionUtils.isEmpty(dto.getRoles())) {
			// Validate role
			dto.getRoles().forEach(RoleType::valueOf);
			// merge role
			List<Role> validRoles = roleRepository.findAll();
			roles = dto.getRoles().stream()
					.map(r -> validRoles.stream()
							.filter(sr -> sr.getName().equals(r))
							.findAny().orElse(new Role(r))
							)
					.collect(Collectors.toSet());
		}
		UserEntity user = UserMapper.mapToUser(dto);
		user.setRoles(roles);
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
