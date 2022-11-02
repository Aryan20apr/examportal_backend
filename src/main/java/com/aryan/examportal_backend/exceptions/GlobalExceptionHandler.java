package com.aryan.examportal_backend.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aryan.examportal_backend.model.JwtRequest;




@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<ExceptionResponse<Map<String, String>>> handleApiException(UserDisabledException exception)

    {
        String message = exception.getMessage();
        Map<String,String> map= new HashMap<String, String>();
        map.put("username", exception.username);
        ExceptionResponse<Map<String, String>> response=new ExceptionResponse<Map<String,String>>(map, message);
        return new ResponseEntity<ExceptionResponse<Map<String, String>>>(response, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse<JwtRequest>> handleApiException(InvalidCredentialException exception)

    {
        String message = exception.getMessage();
        
        ExceptionResponse<JwtRequest> response=new ExceptionResponse<JwtRequest>(exception.jwtRequest, message);
        return new ResponseEntity<ExceptionResponse<JwtRequest>>(response, HttpStatus.NOT_FOUND);

    }
}
