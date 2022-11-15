package com.aryan.examportal_backend.payload;

import java.util.LinkedHashSet;
import java.util.Set;



import com.aryan.examportal_backend.model.Quiz;

import lombok.Data;

@Data
public class CategoryDTO {
Long cid;
	
	String title;
	
	String description;
	
	
	//private Set<QuizDTO> quizes=new LinkedHashSet<>();
}
