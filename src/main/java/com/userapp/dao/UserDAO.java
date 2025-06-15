package com.userapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userapp.model.User;

@Repository 
//This will contain DB Operations. Converts the Low Level Checked Exception to the DataAccess Exception.
public interface UserDAO extends JpaRepository<User, Integer> {

}
