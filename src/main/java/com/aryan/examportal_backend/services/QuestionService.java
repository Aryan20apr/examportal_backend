package com.aryan.examportal_backend.services;

import java.util.List;
import java.util.Set;

import com.aryan.examportal_backend.model.Question;

import com.aryan.examportal_backend.payload.QuestionDTO;

public interface QuestionService {

	public QuestionDTO addQuestion(QuestionDTO question);
	
	public QuestionDTO updateQuestion(QuestionDTO question);
	
	public Set<QuestionDTO> getQuestions();
	
	public QuestionDTO getQuestion(Long id);
	
	public void deleteQuestion(Long questionId);
	
	public List<QuestionDTO> getQuestionsOfQuiz(Long quizId);
	
}
