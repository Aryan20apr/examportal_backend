package com.aryan.examportal_backend.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	@NotNull
	 @NotBlank
		@Size(min = 6,message = "Username cannot be less than 2 characters in length")
	private String username;
	
	@Size(min = 8,max=16,message = "Password must have atleast 6 characters and maximum 16 characters with atleast one Capital letter, special chracter and digit")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
	private String password;
	
	@NotNull
	 @NotBlank
		@Size(min = 2,message = "First name cannot be less than 2 characters in length")
	private String firstname;
		@NotNull
		@NotBlank
		@Size(min = 2,message = "Last name cannot be less than 2 characters in length")
	private String lastname;
	
	@NotNull
		 @NotBlank
	
	
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message="Enter a valid email address")
	private String email;
	private String phone;
	private boolean enabled=true;
	private String profileImage;
	
	Set<UserRoleDTO> userRoles=new HashSet<>();;

}
