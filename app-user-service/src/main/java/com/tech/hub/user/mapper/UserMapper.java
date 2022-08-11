package com.tech.hub.user.mapper;

import org.mapstruct.Mapper;

import com.tech.hub.user.domain.User;
import com.tech.hub.user.dto.UserDTO;

@Mapper
public interface UserMapper {

	User mapToUser(UserDTO dto);

	UserDTO mapToUserDTO(User user);

}
