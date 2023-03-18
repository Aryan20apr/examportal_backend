package com.aryan.examportal_backend.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aryan.examportal_backend.exceptions.NotFoundException;
import com.aryan.examportal_backend.model.Quiz;
import com.aryan.examportal_backend.payload.QuizDTO;
import com.aryan.examportal_backend.repository.QuizRepository;
import com.aryan.examportal_backend.repository.UserRepository;
import com.aryan.examportal_backend.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepository quizRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public QuizDTO addQuiz(QuizDTO quiz,int userId) 
	{
		Quiz quiz1=modelMapper.map(quiz, Quiz.class);
		quiz1.setUser(userRepository.findById((long)(userId)).get());
		Quiz savedQuiz=quizRepository.save(quiz1);
		return modelMapper.map(savedQuiz, QuizDTO.class);
	}

	@Override
	public Set<QuizDTO> getQuizzes() {
		List<Quiz> quizzes=quizRepository.findAll();
		 Set<QuizDTO> categoryDTOs= quizzes.stream().map((cat)-> this.modelMapper.map(cat, QuizDTO.class)).collect(Collectors.toSet());
		 return categoryDTOs;
	}

	@Override
	public QuizDTO getQuiz(Long quizId) {
		Optional<Quiz> quiz=quizRepository.findById(quizId);
		return modelMapper.map(quiz, QuizDTO.class);
	}

	@Override
	public void deleteQuiz(Long quiz) throws NotFoundException {
		try {
			quizRepository.deleteById(quiz);
		} catch (Exception e) {
			throw new NotFoundException("Quiz with this Id do not exist", quiz);
		}
		

	}

	@Override
	public QuizDTO updateQuiz(QuizDTO quiz,long userid) {
		Quiz quiz1=modelMapper.map(quiz, Quiz.class);
		quiz1.setUser(userRepository.findById((long)(userid)).get());
		Quiz savedQuiz=quizRepository.save(quiz1);
		return modelMapper.map(savedQuiz, QuizDTO.class);
	}

	@Override
	public List<QuizDTO> getQuizzesByCategory(Long cid) {
		List<Quiz> quizzesList=quizRepository.findByCategoryCid(cid);
		List<QuizDTO>quizzes= quizzesList.stream().map((cat)-> this.modelMapper.map(cat, QuizDTO.class)).collect(Collectors.toList());
		return quizzes;
	}

	@Override
	public List<QuizDTO> getActiveQuizes() {
		
		List<Quiz> quizList=quizRepository.findActiveQuizes();
		List<QuizDTO>quizzes= quizList.stream().map((quiz)-> this.modelMapper.map(quiz, QuizDTO.class)).collect(Collectors.toList());
		return quizzes;
	}

	@Override
	public List<QuizDTO> getActiveQuizesByCategory(Long cid,Long userid) {
		List<Quiz> quizList=quizRepository.findActiveQuizesByCategoryCid(cid,userid);
		List<QuizDTO>quizzes= quizList.stream().map((quiz)-> this.modelMapper.map(quiz, QuizDTO.class)).collect(Collectors.toList());
		return quizzes;
		
	}

	@Override
	public List<QuizDTO> getQuizzesByUser(Long userid,Long cid) {
		List<Quiz> quizList=quizRepository.findByUserIdOfCategory(userid,cid);
		List<QuizDTO>quizzes= quizList.stream().map((quiz)-> this.modelMapper.map(quiz, QuizDTO.class)).collect(Collectors.toList());
		return quizzes;
	}
	
	

}
