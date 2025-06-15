package com.userapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.userapp.dto.UserDTO;
import com.userapp.model.User;

@Mapper
public interface AutoUserMapper {
	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
	
	UserDTO mapToUserDTO(User user);
	User mapToUser(UserDTO user);

}
