package com.aryan.examportal_backend.model;

import lombok.Data;

@Data
public class JwtRequest //Use this to receive username and password from the user{
{
	String email;
	String password;
	
	
}
