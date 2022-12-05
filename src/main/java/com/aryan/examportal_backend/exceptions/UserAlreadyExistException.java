package com.aryan.examportal_backend.exceptions;

import com.aryan.examportal_backend.payload.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistException extends RuntimeException{

	
	
	
	UserDTO user;
	boolean status;
	
	
	public UserAlreadyExistException(UserDTO username,String message,boolean b)
	{
		super(message);
		this.user=username;
		this.status=b;
	}
}
