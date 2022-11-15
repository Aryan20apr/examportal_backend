package com.aryan.examportal_backend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistException extends RuntimeException{

	
	String username;
	
	
	public UserAlreadyExistException(String username,String message)
	{
		super(message);
		this.username=username;
	}
}
