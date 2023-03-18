package com.aryan.examportal_backend.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long qid;
	
private String description;
	
	private int maxMarks;
	
	private int numberOfQuestions;
	
	private boolean active=false;//By default false
	
	@ManyToOne(fetch = FetchType.EAGER)//Many quizes can belong to single category
	private Category category;
	
	@OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//@JsonIgnore
	private List<Question> questions=new ArrayList<>();
	//private Set<Question> questions=new HashSet<>();
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	private String title;
	
	//@Temporal(TemporalType.TIME)
	private Time quizDuration;
	
	@OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY)
	List<QuizScore> quizScores;
	
	
	
	
	


	
	
	

}
