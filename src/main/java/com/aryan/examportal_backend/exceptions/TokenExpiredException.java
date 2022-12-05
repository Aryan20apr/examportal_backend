package com.aryan.examportal_backend.exceptions;



import org.springframework.http.HttpStatus;

public class TokenExpiredException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	boolean success;
	HttpStatus status;
	public TokenExpiredException()
	{
		super("Token has expired");
		message="Token Expired, Signing out";
		success=false;
		status=HttpStatus.UNAUTHORIZED;
	}

}
