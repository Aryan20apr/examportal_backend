package com.aryan.examportal_backend.payload;



import com.aryan.examportal_backend.model.Quiz;

import lombok.Data;

@Data
public class QuestionDTO {

	private Long qId;
	
	private String content;
	private String image;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	
	
	private QuizDTO quiz;
}
