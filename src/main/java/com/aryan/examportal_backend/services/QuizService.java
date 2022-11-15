package com.aryan.examportal_backend.services;




import java.util.List;
import java.util.Set;

import com.aryan.examportal_backend.exceptions.NotFoundException;
import com.aryan.examportal_backend.payload.QuizDTO;



public interface QuizService {

	public QuizDTO addQuiz(QuizDTO quiz);
	
	public Set<QuizDTO> getQuizzes();
	
	public QuizDTO getQuiz(Long quizId);
	
	public void deleteQuiz(Long quiz) throws NotFoundException;
	
	public QuizDTO updateQuiz(QuizDTO quiz);
	
	public List<QuizDTO> getQuizzesByCategory(Long cid);
}
