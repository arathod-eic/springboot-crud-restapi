package com.userapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userapp.dto.UpdateUserDTO;
import com.userapp.dto.UserDTO;
import com.userapp.service.UserService;
import com.userapp.util.validation.annotation.UserIdPathVariableExists;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Validated
@RestController
@RequestMapping("/api")
public class UserRestController {

	private UserService userService;

	public UserRestController(UserService userService) {
		super();
		this.userService = userService;
	}
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody @Valid UserDTO userDTO) {
		userService.saveUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();		
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable @UserIdPathVariableExists int userId) {
		return ResponseEntity.ok(userService.getUser(userId));
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getUser() {
		return ResponseEntity.ok(userService.getUserList());
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
		return ResponseEntity.status(HttpStatus.OK).build();		
	}
	
	@PatchMapping("/users/{userId}")
	public ResponseEntity<Void> updateUser(@PathVariable int  userId, @RequestBody @Valid UpdateUserDTO userDTO) { //Here we have used the UpdatedUserDTO - used specifically in the patch, where there is no strict policy of all the fields to be present
		userService.updateUser(userDTO);
		return ResponseEntity.status(HttpStatus.OK).build();		
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<Void> updateFullUser(@PathVariable int  userId, @RequestBody @Valid UserDTO userDTO) { //Here we have used the UpdatedUserDTO - used specifically in the patch, where there is no strict policy of all the fields to be present
		userService.updateUser(userDTO);
		return ResponseEntity.status(HttpStatus.OK).build();		
	}
}
