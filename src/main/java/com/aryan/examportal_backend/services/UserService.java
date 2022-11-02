package com.aryan.examportal_backend.services;

import java.util.Set;

import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.model.UserRole;
import com.aryan.examportal_backend.payload.UserDTO;

public interface UserService {

	//creating user
	public UserDTO createUser(UserDTO user)throws Exception;//UserRole contain roles
	
	public UserDTO getUserByUserName(String username);
	
	public void deleteUser(Long userId);
	
	public UserDTO updateUser(UserDTO user,String username);
}
