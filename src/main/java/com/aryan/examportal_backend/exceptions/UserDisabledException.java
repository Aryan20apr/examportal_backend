package com.aryan.examportal_backend.exceptions;

import org.springframework.security.authentication.DisabledException;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDisabledException extends DisabledException
	
{

String username;
	public UserDisabledException(String msg,String username) {
		super(msg);
		this.username=username;
		
	}
	
	

}
