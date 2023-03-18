package com.aryan.examportal_backend.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aryan.examportal_backend.model.JwtRequest;
import com.aryan.examportal_backend.payload.UserDTO;




@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<ExceptionResponse<Map<String, String>>> handleApiException(UserDisabledException exception)

    {
        String message = exception.getMessage();
        Map<String,String> map= new HashMap<String, String>();
        map.put("username", exception.username);
        ExceptionResponse<Map<String, String>> response=new ExceptionResponse<Map<String,String>>(map, message,false);
        return new ResponseEntity<ExceptionResponse<Map<String, String>>>(response, HttpStatus.SEE_OTHER);

    }
    @ExceptionHandler(TokenExpiredException.class)
    public SignoutResponse tokenExpiredException(TokenExpiredException exception)

    {
        
   
       return new SignoutResponse(exception.message,exception.success,exception.status );

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse<JwtRequest>> handleApiException(InvalidCredentialException exception)

    {
        String message = exception.getMessage();
        
        ExceptionResponse<JwtRequest> response=new ExceptionResponse<JwtRequest>(exception.jwtRequest, message,false);
        return new ResponseEntity<ExceptionResponse<JwtRequest>>(response, HttpStatus.OK);

    }
    
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse<Map<String, UserDTO>>> handleApiException(UserAlreadyExistException exception)

    {
        String message = exception.getMessage();
        Map<String,UserDTO> map= new HashMap<>();
        map.put("user", exception.user);
        ExceptionResponse<Map<String, UserDTO>> response=new ExceptionResponse<>(map, message,false);
        return new ResponseEntity<ExceptionResponse<Map<String, UserDTO>>>(response, HttpStatus.OK);

    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse<Map<String, String>>> handleMethodArgNotValidException(MethodArgumentNotValidException exception)
    {
    	Map<String, String> responseMap=new HashMap<>();
    	exception.getBindingResult().getAllErrors().forEach((error)->{
    	String fieldNameString=((FieldError)error).getField();
    	
    	String message=error.getDefaultMessage();
    	responseMap.put(fieldNameString, message);
    		
    	});
    	ExceptionResponse<Map<String, String>> response=new ExceptionResponse<Map<String,String>>(responseMap,exception.getMessage(),false );
    	return new ResponseEntity<ExceptionResponse<Map<String,String>>>(response,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse<Long>> handleNotFoundException(NotFoundException exception)
    {
    	ExceptionResponse<Long> response=new ExceptionResponse<>(exception.id,exception.getMessage(),false);
    	return new ResponseEntity<ExceptionResponse<Long>>(response,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponse<String>> handleUsernameNotFoundException(UsernameNotFoundException exception)
    {
    	ExceptionResponse<String> response=new ExceptionResponse<String>();
    	response.setMessage(exception.getMessage());
    	return new ResponseEntity<ExceptionResponse<String>>(response,HttpStatus.OK);
    }
}
