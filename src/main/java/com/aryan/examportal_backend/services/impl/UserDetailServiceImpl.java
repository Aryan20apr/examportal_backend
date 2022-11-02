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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//or User user
		UserDetails userDetaill=userRepository.findByUsername(username);
		if(userDetaill==null)
		{
			System.out.println("User not found");
			throw new UsernameNotFoundException("User with this username does not exist");
		}
		
		return userDetaill;
	}

}
