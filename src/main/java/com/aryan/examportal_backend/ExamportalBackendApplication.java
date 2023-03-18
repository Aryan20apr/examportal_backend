package com.aryan.examportal_backend;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aryan.examportal_backend.model.Role;
import com.aryan.examportal_backend.repository.RoleRepository;


@SpringBootApplication
public class ExamportalBackendApplication /* implements CommandLineRunner */ {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Autowired
	private static RoleRepository roleRepo;
	
//	 @Autowired private UserService userService;
	 
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static  void main(String[] args) {
		SpringApplication.run(ExamportalBackendApplication.class, args);
		try {
			Role adminrole=new Role();
			adminrole.setRoleId(Constants.ADMIN_ROLE_ID);
			adminrole.setRoleName("ROLE_ADMIN");//Note-This should be written as ROLE_ADMIN only so that it is detected properly when using swagger

			Role normalrole=new Role();
			normalrole.setRoleId(Constants.NORMAL_ROLE_ID);//Note-This should be written as ROLE_NORMAL only so that it is detected properly when using swagger
			normalrole.setRoleName("ROLE_NORMAL");

			List<Role> list=List.of(adminrole,normalrole);
			List<Role> result=roleRepo.saveAll(list);//This will insert the two roles into the table and try to update it if already present
			result.forEach(r->{
				System.out.println(r.getRoleName());
			});

		} catch (Exception e) {
			System.out.println("Role Already Exist");
		}
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
