package com.aryan.examportal_backend.examcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.examportal_backend.ApiResponse2;
import com.aryan.examportal_backend.Constants.PasswordChangeStatus;
import com.aryan.examportal_backend.payload.ApiResponse;
import com.aryan.examportal_backend.payload.CategoryDTO;
import com.aryan.examportal_backend.payload.PasswordChangeDTO;
import com.aryan.examportal_backend.payload.UserDTO;
import com.aryan.examportal_backend.services.UserService;

@RestController
@RequestMapping("examportal/user")
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
	public ResponseEntity<?> getUser(@RequestParam String email) 
		
	
	{
		System.out.println("Username="+email);
		ApiResponse2<UserDTO> response2=new ApiResponse2<UserDTO>();
		UserDTO user =userService.getUserByEmail(email);
		response2.setData(user);
		response2.setMessage("User found");
		response2.setSuccess(true);
		
		return new ResponseEntity<ApiResponse2<UserDTO>>(response2,HttpStatus.OK);
	}
	//delete user by id
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deleteuser")
	public ResponseEntity<ApiResponse<Map<String,String>>> deleteUserById(@RequestParam Long userid)
	
	{
		userService.deleteUser(userid);
		Map<String, String> map=new HashMap<>();
		map.put("message", "User Deleted Successfully");
		
		return new ResponseEntity<ApiResponse<Map<String,String>>>(new ApiResponse(map,HttpStatus.OK,true),HttpStatus.OK); 
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<ApiResponse<UserDTO>> updateUser(@Valid @RequestBody UserDTO updatedUser)
	{
		UserDTO user=userService.updateUser(updatedUser);
		ApiResponse<UserDTO> apiResponse=new ApiResponse<>(user,HttpStatus.ACCEPTED,true);
		return new ResponseEntity<ApiResponse<UserDTO>>(apiResponse,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/changepassword")
    public ResponseEntity<ApiResponse2> changePassword(@Valid @RequestBody PasswordChangeDTO passinfo )
    {
        PasswordChangeStatus b=userService.changePassword(passinfo);
        ApiResponse2 response=new ApiResponse2();
        
        if(PasswordChangeStatus.PASSWORD_CHANGED==b)
        {
            response.setMessage("Password changed successfully");
            response.setSuccess(true);
            return new ResponseEntity<ApiResponse2>(response,HttpStatus.OK);
        }
        else if(PasswordChangeStatus.PASSWORD_INCORRECT==b)
        {
            response.setMessage("Password entered is incorrect");
            response.setSuccess(false);
            return new ResponseEntity<ApiResponse2>(response,HttpStatus.UNAUTHORIZED);
        }
        else {
            response.setMessage("User Does Not exist with this email");
            response.setSuccess(false);
            return new ResponseEntity<ApiResponse2>(response,HttpStatus.UNAUTHORIZED);
        }
        
       
        
    }
	 @PreAuthorize("hasRole('ROLE_ADMIN')")
 	@GetMapping("/getcategorystudents")
     public ResponseEntity<ApiResponse<List<UserDTO>>> getusersEnrolled(@RequestParam long cid)
     {
     	List<UserDTO> userDTOs=userService.findUserInSubject(cid);
     	ApiResponse<List<UserDTO>> response=new ApiResponse<List<UserDTO>>();
     	response.setData(userDTOs);
     	response.setHttpStatus(HttpStatus.OK);
     	response.setSuccess(true);
     	return new ResponseEntity<ApiResponse<List<UserDTO>>>(response,HttpStatus.OK);
     }
	 
	 @PostMapping("/enrollinsubject")
	 public ResponseEntity<ApiResponse<CategoryDTO>> enrollInCategory(@RequestParam long cid,@RequestParam long userid)
	 {
		 CategoryDTO categoryDTO=userService.enrollInCategory(cid, userid);
		 ApiResponse<CategoryDTO> response=new ApiResponse<CategoryDTO>();
		 response.setData(categoryDTO);
		 response.setHttpStatus(HttpStatus.ACCEPTED);
		 response.setSuccess(true);
		 return new ResponseEntity<ApiResponse<CategoryDTO>>(response,HttpStatus.OK);
	 }
	
}
