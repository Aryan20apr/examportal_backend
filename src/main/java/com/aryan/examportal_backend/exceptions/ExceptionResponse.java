package com.aryan.examportal_backend.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionResponse<T> {
	T data;
	String message;
	boolean success;
	public ExceptionResponse(T data, String message,boolean b) {
		
		this.data = data;
		this.message = message;
		this.success=b;
	}
	

}
