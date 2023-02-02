package com.aryan.examportal_backend.services;




import java.util.List;
import java.util.Set;

import com.aryan.examportal_backend.exceptions.NotFoundException;
import com.aryan.examportal_backend.payload.QuizDTO;



public interface QuizService {

	public QuizDTO addQuiz(QuizDTO quiz,int userId);
	
	public Set<QuizDTO> getQuizzes();
	
	public QuizDTO getQuiz(Long quizId);
	
	public List<QuizDTO> getQuizzesByUser(Long userid,Long cid);
	
	public void deleteQuiz(Long quiz) throws NotFoundException;
	
	public QuizDTO updateQuiz(QuizDTO quiz,long userid);
	
	public List<QuizDTO> getQuizzesByCategory(Long cid);
	
	public List<QuizDTO> getActiveQuizes();
	
	public List<QuizDTO> getActiveQuizesByCategory(Long cid,Long userid);//user id is required to find un-attempted  quizzes
}
