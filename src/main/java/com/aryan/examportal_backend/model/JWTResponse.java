package com.aryan.examportal_backend.model;

import lombok.Data;

@Data
public class JWTResponse<T> 


{
	T data;
	//If the username password obtained using the JWTRequest and is authenticated, token is returned using this class 
		String token;
		boolean success;
	public JWTResponse(T data,String token,boolean b) {
		super();
		 this.data=data;
		this.token = token;
		success=b;
		
	}

	
}
