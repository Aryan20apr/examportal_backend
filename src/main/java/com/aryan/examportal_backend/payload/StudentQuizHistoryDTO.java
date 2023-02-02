package com.aryan.examportal_backend.payload;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class StudentQuizHistoryDTO {
QuizDTO quiz;
	
	private double marks;
	
	private int attempted;
	
	private int correct;
	
	private int rating;
	
	
	private Timestamp timestamp;
}
