package com.aryan.examportal_backend.exceptions;



import com.aryan.examportal_backend.model.JwtRequest;

public class NotFoundException extends Exception{

	Long id;
	public NotFoundException(String msg,Long x) {
		super(msg);
		id=x;
	}
	

}
