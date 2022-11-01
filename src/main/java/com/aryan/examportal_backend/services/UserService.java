package com.aryan.examportal_backend.services;

import java.util.Set;

import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.model.UserRole;

public interface UserService {

	//creating user
	public User createUser(User user,Set<UserRole> userRoles)throws Exception;//UserRole contain roles
	
	
}
