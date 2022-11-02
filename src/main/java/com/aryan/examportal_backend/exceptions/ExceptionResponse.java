package com.aryan.examportal_backend.exceptions;

import lombok.Data;

@Data
public class ExceptionResponse<T> {
	T data;
	String message;
	public ExceptionResponse(T data, String message) {
		
		this.data = data;
		this.message = message;
	}
	

}
