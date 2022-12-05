package com.aryan.examportal_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aryan.examportal_backend.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("Load User By Email "+email);
		//or User user
		UserDetails userDetails1=userRepository.findByEmail(email);
		
		if(userDetails1==null)
		{
			
			System.out.println("User not found");
			throw new UsernameNotFoundException("User with this email does not exist");
		}
		
		return userDetails1;
	}

}
