package com.aryan.examportal_backend.services;

import java.util.List;
import java.util.Set;

import com.aryan.examportal_backend.payload.QuestionDTO;

public interface QuestionService {

	public QuestionDTO addQuestion(QuestionDTO question,Long qid);
	
	public QuestionDTO updateQuestion(QuestionDTO question/* ,long qid */);
	
	public Set<QuestionDTO> getQuestions();
	
	public QuestionDTO getQuestion(Long id);
	
	public void deleteQuestion(Long questionId);
	
	public List<QuestionDTO> getQuestionsOfQuiz(Long quizId);
	public List<QuestionDTO> getAllQuestionsOfQuiz(Long quizId);
	
	//public QuizResultDTO evaluateQuiz(List<QuestionDTO> responses);
}
