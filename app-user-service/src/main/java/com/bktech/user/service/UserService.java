package com.bktech.user.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bktech.user.Constants;
import com.bktech.user.data.AdminRepository;
import com.bktech.user.data.UserRepository;
import com.bktech.user.domain.Role;
import com.bktech.user.domain.UserEntity;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.execp.AppException;
import com.bktech.user.execp.ExceptionCode;
import com.bktech.user.mapper.UserMapper;
import com.bktech.user.vo.UserVO;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserVO getUser(String userName) {

		return userRepository.findByUsername(userName)
				.map(UserMapper::mapToUserVO)
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0005, userName));
	}

	public UserVO updateUser(UserDTO dto) {
		UserEntity newUuser = UserMapper.mapToUser(dto);
		UserEntity savedUser = userRepository.save(newUuser);
		return UserMapper.mapToUserVO(savedUser);
	}

	public List<UserVO> getAllUser() {
		return userRepository.findAll()
				.stream()
				.map(UserMapper::mapToUserVO)
				.collect(Collectors.toList());
	}

	public String deleteUser(String userName) {
		userRepository.deleteByUsername(userName);
		return Constants.SUCCESS;
	}

	public UserVO registerUser(UserDTO dto) {
		if(userRepository.existsByUsername(dto.getUsername())) {
			throw new AppException(ExceptionCode.USRSVC_0007, dto.getUsername());
		}
		Set<Role> roles = null;
		if(!CollectionUtils.isEmpty(dto.getRoles())) {
			roles = adminRepository.findAll().stream()
					.filter(r -> dto.getRoles().contains(r.getName()))
					.collect(Collectors.toSet());
			if(roles.size() != dto.getRoles().size()) {
				throw new AppException(ExceptionCode.USRSVC_0008);
			}
		}
		UserEntity user = UserMapper.mapToUser(dto);
		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		return UserMapper.mapToUserVO(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
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
