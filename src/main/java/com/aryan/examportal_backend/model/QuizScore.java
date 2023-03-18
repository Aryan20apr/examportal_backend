package com.aryan.examportal_backend.model;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuizScore {

	@EmbeddedId
	QuizMarksKey marksId;
	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name="user_id")
	User user;
	
	@ManyToOne
	@MapsId("qid")
	@JoinColumn(name="quiz_id")
	Quiz quiz;
	
	private double marks;
	
	private int attempted;
	
	private int correct;
	
	private int rating;
	
	
	private Timestamp timestamp;
	
	
}
