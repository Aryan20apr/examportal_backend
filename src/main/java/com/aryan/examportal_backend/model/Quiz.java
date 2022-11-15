package com.aryan.examportal_backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long qid;
	
	private String title;
	
	private String description;
	
	private String maxMarks;
	
	private int numberOfQuestions;
	
	private boolean active=false;//By default false
	
	@ManyToOne(fetch = FetchType.EAGER)//Many quizes can belong to single category
	private Category category;
	
	@OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//@JsonIgnore
	private Set<Question> questions=new HashSet<>();
	

}
