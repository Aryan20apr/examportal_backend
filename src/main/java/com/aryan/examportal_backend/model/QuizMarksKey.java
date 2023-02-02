package com.aryan.examportal_backend.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class QuizMarksKey implements Serializable {
	
	@Column(name="user_id")
	Long id;
	
	@Column(name="quiz_id")
	Long qid;

	@Override
	public int hashCode() {
		return Objects.hash(id, qid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizMarksKey other = (QuizMarksKey) obj;
		return Objects.equals(id, other.id) && Objects.equals(qid, other.qid);
	}



	

}
