package com.aryan.examportal_backend.services;



import java.util.List;

import com.aryan.examportal_backend.Constants.PasswordChangeStatus;
import com.aryan.examportal_backend.payload.CategoryDTO;
import com.aryan.examportal_backend.payload.PasswordChangeDTO;
import com.aryan.examportal_backend.payload.UserDTO;

public interface UserService {

	//creating 
	public UserDTO createUser(UserDTO user)throws Exception;//UserRole contain roles
	
	UserDTO registerNewUser(UserDTO user);
	
	public UserDTO getUserByNickname(String username);
	
	public UserDTO getUserByEmail(String email);
	
	public void deleteUser(Long userId);
	
	public UserDTO updateUser(UserDTO user);
	
	PasswordChangeStatus changePassword(PasswordChangeDTO passwordChangeDTO);

	PasswordChangeStatus forgotPassword(PasswordChangeDTO passwordChangeDTO);

	public UserDTO registerAdmin(UserDTO userdto);
	
	public List<UserDTO> findUserInSubject(long userid);

	public CategoryDTO enrollInCategory(long cid,long userid);
	
	
}
