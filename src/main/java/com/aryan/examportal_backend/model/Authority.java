package com.aryan.examportal_backend.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority//This should be umplemented as the return type of getAuthoritites of UserDetail is extending this interface{
{
	
	public Authority(String authority) {
	
		this.authority = authority;
	}
	private String authority;
	@Override
	public String getAuthority()
	{//called internally by spring security{
		// TODO Auto-generated method stub
		return authority;
	}
	

}
