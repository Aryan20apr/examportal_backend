package com.aryan.examportal_backend.exceptions;

import org.springframework.security.authentication.BadCredentialsException;

import com.aryan.examportal_backend.model.JwtRequest;

public class InvalidCredentialException extends BadCredentialsException{

	JwtRequest jwtRequest;
	public InvalidCredentialException(String msg,JwtRequest jwtRequest) {
		super(msg);
		this.jwtRequest=jwtRequest;
	}
	

}
