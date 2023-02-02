package com.aryan.examportal_backend.payload;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizScoreDTO {

private double marks;
	
	private int attempted;
	
	private int correct;
	
	private int rating;
	
	
	private Timestamp timestamp;
	
	private boolean result;
	
}
