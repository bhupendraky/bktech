package com.tech.hub.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tech.hub.user.domain.User;
import com.tech.hub.user.dto.UserDTO;

@Mapper
public interface UserMapper {

	@Mapping(target = "id", ignore = true)
	User mapToUser(UserDTO dto);

	UserDTO mapToUserDTO(User user);

}
