package com.aryan.examportal_backend.services.impl;

import java.util.Set;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.model.UserRole;
import com.aryan.examportal_backend.repository.RoleRepository;
import com.aryan.examportal_backend.repository.UserRepository;
import com.aryan.examportal_backend.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;//Object of implementation class of UserRepository
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser (User user, Set<UserRole> userRoles)throws Exception {
		
		User exisitingUser=this.userRepository.findByUsername(user.getUsername());
		if(exisitingUser!=null) {
			System.out.println("User is already present");
			throw new Exception("User Already Present");
		}
		else {
			for(UserRole ur:userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			exisitingUser=this.userRepository.save(user);
		}
		
		return exisitingUser;
	}

}
