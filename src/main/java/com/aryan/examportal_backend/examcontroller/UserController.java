package com.aryan.examportal_backend.examcontroller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.examportal_backend.model.Role;
import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.model.UserRole;
import com.aryan.examportal_backend.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	//creating user
	@Autowired
	private UserService userService;
	@PostMapping("/")
	public User createUser(@RequestBody User user ) throws Exception
	{
		Set<UserRole> roles=new HashSet<>();
		Role role=new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");//Every user who sign up will have a NORMAL ROLE
		
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole );
		return this.userService.createUser(user, roles);
	}
	
}
