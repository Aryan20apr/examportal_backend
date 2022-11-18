package com.aryan.examportal_backend.examcontroller;



import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.examportal_backend.ApiResponse2;
import com.aryan.examportal_backend.Constants.PasswordChangeStatus;
import com.aryan.examportal_backend.OTPSentResponse;
import com.aryan.examportal_backend.config.JwtUtils;
import com.aryan.examportal_backend.exceptions.InvalidCredentialException;
import com.aryan.examportal_backend.exceptions.UserDisabledException;
import com.aryan.examportal_backend.model.JWTResponse;
import com.aryan.examportal_backend.model.JwtRequest;
import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.payload.PasswordChangeDTO;
import com.aryan.examportal_backend.payload.UserDTO;
import com.aryan.examportal_backend.services.UserService;
import com.aryan.examportal_backend.services.impl.EmailService;
import com.aryan.examportal_backend.services.impl.OTPService;
import com.aryan.examportal_backend.services.impl.UserDetailServiceImpl;



@RestController
@RequestMapping("/examportal/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired 
	private OTPService otpService;
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	    private EmailService emailService;
	
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
	public User  getCurrentUser(Principal principal)
	{
		
		return (User)userDetailService.loadUserByUsername(principal.getName());
	}
	
	
	    
//	    @PostMapping("/verifyEmail")
//	    public ResponseEntity<?> verifyEmail(@RequestBody UserDTO user)
//	    {Map<String,String> map=new HashMap<String, String>();
//	        boolean sent=emailService.sendEmail(user.getEmail(),user.getEmail());
//	        
//	        
//	            
//	            map.put("vrified", sent+"");
//	            map.put("email", user.getEmail());
//	        
//	        return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
//	           
//	        
//	    }
	    
	    @PostMapping("/sendotp")
	    public ResponseEntity<?> sendOTP(@RequestParam String email, HttpSession session) {
	        // Generate OTP of 6 digit

	        // int otp=random.nextInt(1000000);
	        // session.setAttribute("otp", otp);
	        
	        session.setAttribute("email", email);
	        
	        
	        boolean b = emailService.sendEmail(email);

	        if (b) {
	            OTPSentResponse response = new OTPSentResponse(email, "OTP Sent Successfully");
	            return new ResponseEntity<OTPSentResponse>(response, HttpStatus.OK);
	        } else {
	            OTPSentResponse response = new OTPSentResponse(email, "Could not send otp. Enter a valid email id.");
	            return new ResponseEntity<OTPSentResponse>(response, HttpStatus.OK);
	        }
	    }

	    @PostMapping("/verifyotp")
	    public ResponseEntity<?> verifyOTP(@RequestParam int otp, HttpSession session) {
	        // Integer myOtp=(int)session.getAttribute("otp");

	        String email = (String) session.getAttribute("email");// We can use session to remeber ceratin things
	        int serverOtp = otpService.getOtp(email);
	        ApiResponse2 response = new ApiResponse2();

	        System.out.println("Otp is " + serverOtp + " " + otp);

	        if (serverOtp == otp) {
	            System.out.println("##$%^");
	            response.setMessage("Email verified");
	            response.setSuccess(true);
	            otpService.clearOTP(email);

	        } else {

	            response.setMessage("OTP does not match");
	            response.setSuccess(false);
	        }
	        return new ResponseEntity<ApiResponse2>(response, HttpStatus.UNAUTHORIZED);
	    }
	    
	    @PostMapping("/forgotpassword")
	    public ResponseEntity<ApiResponse2> changePassword(@Valid @RequestBody PasswordChangeDTO passinfo )
	    {
	        
	        PasswordChangeStatus b=userService.forgotPassword(passinfo);
	        ApiResponse2 response=new ApiResponse2();
	        
	        if(PasswordChangeStatus.PASSWORD_CHANGED==b)
	        {
	            response.setMessage("Password changed successfully");
	            response.setSuccess(true);
	            return new ResponseEntity<ApiResponse2>(response,HttpStatus.OK);
	        }
	     
	        else {
	            response.setMessage("User Does Not exist with this email");
	            response.setSuccess(false);
	            return new ResponseEntity<ApiResponse2>(response,HttpStatus.UNAUTHORIZED);
	        }
	        
	    }
	    @PostMapping("/register")
	    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
	        UserDTO registeredUser = userService.registerNewUser(userDTO);
	        return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.CREATED);

	    }
	
}
