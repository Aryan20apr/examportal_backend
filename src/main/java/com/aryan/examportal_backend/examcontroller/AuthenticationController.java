package com.aryan.examportal_backend.examcontroller;



import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.examportal_backend.config.JwtUtils;
import com.aryan.examportal_backend.exceptions.InvalidCredentialException;
import com.aryan.examportal_backend.exceptions.UserDisabledException;
import com.aryan.examportal_backend.model.JWTResponse;
import com.aryan.examportal_backend.model.JwtRequest;
import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.payload.UserDTO;
import com.aryan.examportal_backend.services.impl.UserDetailServiceImpl;



@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	private void authenticate(JwtRequest jwtRequest) throws Exception
	{//Use Runtime Exception instead for extending the custom exception classes and do not use try catch
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		} catch (DisabledException e) {
			throw new UserDisabledException("User account disabled", jwtRequest.getUsername());
		}
		catch (BadCredentialsException e) {
			
			throw new InvalidCredentialException("Invalid Credentials",jwtRequest);
		}
	}
	// generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest ) throws Exception
	{
			try {
				authenticate(jwtRequest);
			} catch (UsernameNotFoundException e) {
						e.printStackTrace();
						throw new Exception("User Not Found");
			}
			//User authenticated
			UserDetails userDetails= userDetailService.loadUserByUsername(jwtRequest.getUsername());
			String token=jwtUtils.generateToken(userDetails);
			
			return ResponseEntity.ok(new JWTResponse(token));
	}
	@GetMapping("/current-user")
	public UserDTO  getCurrentUser(Principal principal)
	{
		return (UserDTO)userDetailService.loadUserByUsername(principal.getName());
	}
	
}
