package com.aryan.examportal_backend.payload;

import java.util.HashSet;
import java.util.Set;

import com.aryan.examportal_backend.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private boolean enabled=true;
	private String profileImage;
	
	Set<UserRoleDTO> userRoles=new HashSet<>();;

}
