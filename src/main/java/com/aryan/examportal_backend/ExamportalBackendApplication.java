package com.aryan.examportal_backend;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aryan.examportal_backend.model.Role;
import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.model.UserRole;
import com.aryan.examportal_backend.services.UserService;

@SpringBootApplication
public class ExamportalBackendApplication /* implements CommandLineRunner */ {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
//	 @Autowired private UserService userService;
	 
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ExamportalBackendApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Starting Code");
//		User user = new User();
//
//		user.setFirstName("Aryan");
//		user.setLastName("Singh");
//		user.setEmail("aryansingh@email.com");
//		user.setUsername("Aryan@194");
//		user.setPassword("abc");
//		user.setProfileImage("default.png");
//		user.setPhone("9548084757");
//
//		Role role = new Role();
//		role.setRoleId(44L);
//		role.setRoleName("ADMIN");
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//
//		User user1 = userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());
//	}

}
