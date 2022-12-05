package com.aryan.examportal_backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qId;
	@Column(length=5000)
	private String content;
	private String image;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	
	@Transient //This prevents this column from being created in the database
	private String givenAnswer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Quiz quiz;
}
