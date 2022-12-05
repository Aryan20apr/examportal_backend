package com.aryan.examportal_backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	
	
	
	


	
	
	

}
