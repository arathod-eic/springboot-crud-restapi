package com.userapp.util.validator;

import com.userapp.dao.UserDAO;
import com.userapp.util.validation.annotation.UserIdPathVariableExists;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserIdPathVarialbeExistsValidator implements ConstraintValidator<UserIdPathVariableExists, Integer> {

	private UserDAO userDAO;
	
	public UserIdPathVarialbeExistsValidator(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value != null) {
			return userDAO.findById(value).isPresent();
		}
		return false;
	}

}
