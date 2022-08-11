package com.tech.hub.user.mapper;

import org.mapstruct.Mapper;

import com.tech.hub.user.domain.User;
import com.tech.hub.user.dto.UserDTO;

@Mapper
public interface UserMapper {

	User userDtoToUserMapper(UserDTO dto);

	UserDTO userToUserDtoMapper(User user);
}
