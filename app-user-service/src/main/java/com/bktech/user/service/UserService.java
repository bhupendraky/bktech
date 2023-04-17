package com.bktech.user.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bktech.user.Constants;
import com.bktech.user.data.UserRepository;
import com.bktech.user.domain.User;
import com.bktech.user.dto.AppUserDetails;
import com.bktech.user.dto.UserDTO;
import com.bktech.user.execp.AppException;
import com.bktech.user.execp.ExceptionCode;
import com.bktech.user.mapper.UserMapper;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDTO getUser(String userName) {

		return userRepository.findByUserName(userName)
				.map(UserMapper::mapToUserDTO)
				.orElseThrow(() -> new AppException(ExceptionCode.USRSVC_0005, userName));
	}

	public UserDTO updateUser(UserDTO dto) {
		User newUuser = UserMapper.mapToUser(dto);
		User savedUser = userRepository.save(newUuser);
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

	public UserDTO createUser(UserDTO dto) {
		User newUser = UserMapper.mapToUser(dto);
		User savedUser = userRepository.save(newUser);
		return UserMapper.mapToUserDTO(savedUser);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return userRepository.findByUserName(userName)
				.map(AppUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
	}

	public Map<Integer, List<UserDTO>> getUsersGroupedByAge() {
		return userRepository.findAll()
				.stream()
				.collect(Collectors.groupingBy(
						User::getAge,
						LinkedHashMap::new,
						Collectors.mapping(UserMapper::mapToUserDTO, Collectors.toList()))
						);
	}
}
