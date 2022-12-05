package com.aryan.examportal_backend.exceptions;





public class NotFoundException extends Exception{

	Long id;
	public NotFoundException(String msg,Long x) {
		super(msg);
		id=x;
	}
	

}
