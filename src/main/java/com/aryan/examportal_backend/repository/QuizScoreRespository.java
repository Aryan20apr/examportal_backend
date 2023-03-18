package com.aryan.examportal_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aryan.examportal_backend.model.Quiz;
import com.aryan.examportal_backend.model.QuizMarksKey;
import com.aryan.examportal_backend.model.QuizScore;
import com.aryan.examportal_backend.model.User;

public interface QuizScoreRespository extends JpaRepository<QuizScore, QuizMarksKey>{

	public List<QuizScore> findByQuiz(Quiz quiz);
	public List<QuizScore> findByUser(User user);
}
