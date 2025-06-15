package com.userapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userapp.dao.UserDAO;
import com.userapp.dto.UpdateUserDTO;
import com.userapp.dto.UserDTO;
import com.userapp.exception.UserNotFoundException;
import com.userapp.mapper.AutoUserMapper;
import com.userapp.model.User;
import com.userapp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j // creates loggers for the class, no need for manual object creation
@Service // Denotes that this class will handle the business logic, similar to Component
			// just for better readability
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;

	public UserServiceImpl(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}

	@Transactional
	@Override
	public void saveUser(UserDTO userDTO) {
		
		if(userDTO.getId() != null) {
			throw new IllegalArgumentException("ID should not be provided when creating a new user");
		}
		User newUser = AutoUserMapper.MAPPER.mapToUser(userDTO);
		userDAO.save(newUser);
	}

	@Transactional
	@Override
	public UserDTO updateUser(UpdateUserDTO userDTO) {
		//In rest controller, no custom annotation used, so, here the error UserNotFoundException can be thrown
		User user = userDAO.findById(userDTO.getId()).orElseThrow(() -> new UserNotFoundException(String.valueOf(userDTO.getId()))); 
		
		//setting the updated field
		if(userDTO.getFirstName() !=null) {
			user.setFirstName(userDTO.getFirstName());
		}
		if(userDTO.getLastName() !=null) {
			user.setLastName(userDTO.getLastName());
		}
		if(userDTO.getLastName() !=null) {
			user.setLastName(userDTO.getLastName());
		}
		if(userDTO.getEmail() !=null) {
			user.setEmail(userDTO.getEmail());
		}
		if(userDTO.getAge() != null) {
			user.setAge(userDTO.getAge());
		}
		
		userDAO.save(user); //explicit saving for better readability
		UserDTO updatedUserDTO = AutoUserMapper.MAPPER.mapToUserDTO(user);
		return updatedUserDTO;
	}
	
	@Transactional
	@Override
	public UserDTO updateFullUser(UserDTO userDTO) {
		//In rest controller, no custom annotation used, so, here the error UserNotFoundException can be thrown
		User user = userDAO.findById(userDTO.getId()).orElseThrow(() -> new UserNotFoundException(String.valueOf(userDTO.getId()))); 
		
		//setting the updated field
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setAge(userDTO.getAge());
		
		userDAO.save(user); //explicit saving for better readability
		UserDTO updatedUserDTO = AutoUserMapper.MAPPER.mapToUserDTO(user);
		return updatedUserDTO;
	}

	@Transactional
	@Override
	public void deleteUser(int id) {
		userDAO.deleteById(id); //No Exception is thrown if id is not present, and is silently ignored
		
		//Another way is to fetch the entity and then delete it to show custom exception such as user not found

	}

	@Override
	public UserDTO getUser(int id) {
		//Custom validation annotation used in the Controller, UserNotFoundException won't get called.
		User user = userDAO.findById(id).orElseThrow(() -> new UserNotFoundException(String.valueOf(id))); 
		UserDTO userDTO = AutoUserMapper.MAPPER.mapToUserDTO(user);
		return userDTO;
	}

	@Override
	public List<UserDTO> getUserList() {
		List<User> users = userDAO.findAll();
		return users.stream().map(AutoUserMapper.MAPPER::mapToUserDTO).toList();
	}

}
