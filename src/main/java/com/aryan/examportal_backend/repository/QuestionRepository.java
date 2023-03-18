package com.aryan.examportal_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aryan.examportal_backend.model.Question;
import com.aryan.examportal_backend.model.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	List<Question> findByQuiz(Optional<Quiz> quiz);// The corresponding field is Quiz quiz in model, therefore Include that in camel case
}
