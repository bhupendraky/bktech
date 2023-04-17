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
import org.springframework.stereotype.Service;

import com.bktech.user.Constants;
import com.bktech.user.data.AdminRepository;
import com.bktech.user.data.UserRepository;
import com.bktech.user.domain.Role;
import com.bktech.user.domain.UserEntity;
import com.bktech.user.dto.AppUserDetails;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.execp.AppException;
import com.bktech.user.execp.ExceptionCode;
import com.bktech.user.mapper.UserMapper;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;

	public UserDTO getUser(String userName) {

		return userRepository.findByUserName(userName)
				.map(UserMapper::mapToUserDTO)
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0005, userName));
	}

	public UserDTO updateUser(UserDTO dto) {
		UserEntity newUuser = UserMapper.mapToUser(dto);
		UserEntity savedUser = userRepository.save(newUuser);
		return UserMapper.mapToUserDTO(savedUser);
	}

	public List<UserDTO> getAllUser() {
		return userRepository.findAll()
				.stream()
				.map(UserMapper::mapToUserDTO)
				.collect(Collectors.toList());
	}

	public String deleteUser(String userName) {
		userRepository.deleteByUserName(userName);
		return Constants.SUCCESS;
	}

	public UserDTO registerUser(UserDTO dto) {
		if(userRepository.existsByUserName(dto.getUserName())) {
			throw new AppException(ExceptionCode.USRSVC_0007, dto.getUserName());
		}
		Set<Role> matchingRoles = adminRepository.findAll().stream()
				.filter(r -> dto.getRoles().contains(r.getName()))
				.collect(Collectors.toSet());
		if(matchingRoles.size() != dto.getRoles().size()) {
			throw new AppException(ExceptionCode.USRSVC_0008);
		}

		UserEntity user = UserMapper.mapToUser(dto);
		user.setRoles(matchingRoles);
		user = userRepository.save(user);
		return UserMapper.mapToUserDTO(user);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return userRepository.findByUserName(userName)
				.map(AppUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + userName));
	}

	public Map<Integer, List<UserDTO>> getUsersGroupedByAge() {
		return userRepository.findAll()
				.stream()
				.collect(Collectors.groupingBy(
						UserEntity::getAge,
						LinkedHashMap::new,
						Collectors.mapping(UserMapper::mapToUserDTO, Collectors.toList()))
						);
	}
}
