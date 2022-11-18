package com.aryan.examportal_backend.services;



import com.aryan.examportal_backend.Constants.PasswordChangeStatus;

import com.aryan.examportal_backend.payload.PasswordChangeDTO;
import com.aryan.examportal_backend.payload.UserDTO;

public interface UserService {

	//creating user
	public UserDTO createUser(UserDTO user)throws Exception;//UserRole contain roles
	
	UserDTO registerNewUser(UserDTO user);
	
	public UserDTO getUserByUserName(String username);
	
	public void deleteUser(Long userId);
	
	public UserDTO updateUser(UserDTO user,String username);
	
	PasswordChangeStatus changePassword(PasswordChangeDTO passwordChangeDTO);

	PasswordChangeStatus forgotPassword(PasswordChangeDTO passwordChangeDTO);
}
