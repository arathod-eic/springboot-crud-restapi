package com.userapp.service;

import java.util.List;

import com.userapp.dto.UpdateUserDTO;
import com.userapp.dto.UserDTO;

public interface UserService {

	void saveUser(UserDTO userDTO);

	UserDTO updateUser(int userId,UpdateUserDTO userDTO); //used UpdatedUserDTO, because, in update it's possible that one or more field can be null.

	UserDTO updateFullUser(int userId, UserDTO userDTO);
	
	void deleteUser(int id);

	UserDTO getUser(int id);

	List<UserDTO> getUserList();

}
