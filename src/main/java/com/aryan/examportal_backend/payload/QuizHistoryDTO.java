package com.aryan.examportal_backend.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizHistoryDTO {

	Long qid;
	
	private String title;
	
	private String description;
	
	private int maxMarks;
	
	private int numberOfQuestions;
	
	
}
