package com.aryan.examportal_backend.examcontroller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.examportal_backend.model.Role;
import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.model.UserRole;
import com.aryan.examportal_backend.payload.ApiResponse;
import com.aryan.examportal_backend.payload.UserDTO;
import com.aryan.examportal_backend.services.UserService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;

import lombok.var;

@RestController
@RequestMapping("/user")
//@CrossOrigin("*") 
public class UserController {

	//creating user
	@Autowired
	private UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	@PostMapping("/newuser")
	public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO user ) throws Exception
	{
		
		UserDTO newUser=userService.createUser(user);
		ApiResponse<UserDTO> apiResponse=new ApiResponse<>(newUser,HttpStatus.CREATED,true);
		return new ResponseEntity<ApiResponse<UserDTO>>(apiResponse,HttpStatus.CREATED);
	}
	//Getting user by username
	@GetMapping("/getUser")
	public UserDTO getUser(@RequestParam String username) 
		
	
	{
		System.out.println("Username="+username);
		
		return userService.getUserByUserName(username);
	}
	//delete user by id
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteUser")
	public ResponseEntity<ApiResponse<Map<String,String>>> deleteUserById(@RequestParam Long userid)
	
	{
		userService.deleteUser(userid);
		Map<String, String> map=new HashMap<>();
		map.put("message", "User Deleted Successfully");
		
		return new ResponseEntity<ApiResponse<Map<String,String>>>(new ApiResponse(map,HttpStatus.OK,true),HttpStatus.OK); 
	}
	
	@PutMapping("/updateUser/{username}")
	public ResponseEntity<ApiResponse<UserDTO>> updateUser(@Valid @RequestBody UserDTO updatedUser,@PathVariable String username)
	{
		UserDTO user=userService.updateUser(updatedUser,username);
		ApiResponse<UserDTO> apiResponse=new ApiResponse<>(user,HttpStatus.ACCEPTED,true);
		return new ResponseEntity<ApiResponse<UserDTO>>(apiResponse,HttpStatus.ACCEPTED);
	}
	
	
	
	
}
