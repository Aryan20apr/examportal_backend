package com.aryan.examportal_backend.payload;

import java.util.HashSet;
import java.util.Set;



import com.aryan.examportal_backend.model.Category;
import com.aryan.examportal_backend.model.Question;

import lombok.Data;


@Data
public class QuizDTO {

	Long qid;
	
	private String title;
	
	private String description;
	
	private String maxMarks;
	
	private int numberOfQuestions;
	
	private boolean active=false;
	
	
	private CategoryDTO category;
	
	
	//private Set<QuestionDTO> questions=new HashSet<>();
}
