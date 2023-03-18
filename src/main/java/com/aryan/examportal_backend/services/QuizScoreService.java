package com.aryan.examportal_backend.services;

import java.util.List;

import com.aryan.examportal_backend.payload.AdminQuizHistoryDTO;
import com.aryan.examportal_backend.payload.QuestionDTO;
import com.aryan.examportal_backend.payload.QuizScoreDTO;
import com.aryan.examportal_backend.payload.StudentQuizHistoryDTO;

public interface QuizScoreService {

	
	public QuizScoreDTO evaluateQuiz(List<QuestionDTO> responses,Long userid,Long quizId);
	
	public List<AdminQuizHistoryDTO> getQuizAttempts(Long qid);
	
	public List<StudentQuizHistoryDTO> getQuizAttemptsOfUser(Long id);
}
