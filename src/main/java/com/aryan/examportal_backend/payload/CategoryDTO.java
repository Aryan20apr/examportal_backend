package com.aryan.examportal_backend.payload;

import lombok.Data;

@Data
public class CategoryDTO {
Long cid;
	
	String title;
	
	String description;
	
	
	//private Set<QuizDTO> quizes=new LinkedHashSet<>();
	
	//private UserDTO user;
}
