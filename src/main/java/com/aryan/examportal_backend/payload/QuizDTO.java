package com.aryan.examportal_backend.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class QuizDTO {

	Long qid;
	
	private String title;
	
	private String description;
	
	private int maxMarks;
	
	private int numberOfQuestions;
	
	private boolean active=false;
	
	
	private CategoryDTO category;
	
	@JsonIgnore
	private UserDTO user;

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}

	

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public UserDTO getUser() {
		System.out.println("getUser called");
		return user;
	}

	public void setUser(UserDTO user) {
		System.out.println("setUser called");
		
		this.user =user;
	}
	
	
	
	
	//private Set<QuestionDTO> questions=new HashSet<>();
}
