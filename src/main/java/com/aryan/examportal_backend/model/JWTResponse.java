package com.aryan.examportal_backend.model;

import lombok.Data;

@Data
public class JWTResponse 

{
	public JWTResponse(String token) {
		super();
		this.token = token;
	}

	//If the username password obtained using the JWTRequest and is authenticated, token is returned using this class 
	String token;
}
