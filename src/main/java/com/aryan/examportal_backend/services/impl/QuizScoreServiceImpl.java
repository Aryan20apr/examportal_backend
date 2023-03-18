package com.aryan.examportal_backend.services.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aryan.examportal_backend.model.Question;
import com.aryan.examportal_backend.model.Quiz;
import com.aryan.examportal_backend.model.QuizMarksKey;
import com.aryan.examportal_backend.model.QuizScore;
import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.payload.AdminQuizHistoryDTO;
import com.aryan.examportal_backend.payload.QuestionDTO;
import com.aryan.examportal_backend.payload.QuizScoreDTO;
import com.aryan.examportal_backend.payload.StudentQuizHistoryDTO;
import com.aryan.examportal_backend.repository.QuestionRepository;
import com.aryan.examportal_backend.repository.QuizRepository;
import com.aryan.examportal_backend.repository.QuizScoreRespository;
import com.aryan.examportal_backend.repository.UserRepository;
import com.aryan.examportal_backend.services.QuizScoreService;

@Service
public class QuizScoreServiceImpl implements QuizScoreService {
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired QuizRepository quizRepository;
	
	@Autowired UserRepository userRepository;
	@Autowired
	private QuizScoreRespository quizScoreRepository;
	
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public QuizScoreDTO evaluateQuiz(List<QuestionDTO> responses,Long userid,Long quizid) {
		
		double totalMarksObtained=0.0;
		Integer correctAnswers=0;
		Integer attempted=0;
		double passpercent=40.0;
		int maxMarks=0;
		for(QuestionDTO q: responses)
		{
			Question question=	questionRepository.findById(q.getQId()).get();
			if(q.getGivenAnswer()==null)
				continue;
			if(question.getAnswer().trim().equals(q.getGivenAnswer().trim()))
			{
				correctAnswers++;
				
				maxMarks=question.getQuiz().getMaxMarks();
				double marksSingle=maxMarks/question.getQuiz().getNumberOfQuestions();
				System.out.println("maxMarks="+maxMarks+ "number of questions "+question.getQuiz().getNumberOfQuestions());
				totalMarksObtained+=marksSingle;
			}
			if(q.getGivenAnswer()!=null||!q.getGivenAnswer().trim().equals("")) {
			attempted++;
			}
			
		}
		
		Quiz quiz=quizRepository.findById(quizid).get();
		User user=userRepository.findById(userid).get();
		
		QuizMarksKey quizMarksKey=new QuizMarksKey();
		quizMarksKey.setId(quizid);
		quizMarksKey.setQid(userid);
		
		
		
		QuizScore quizScore=new QuizScore();
		quizScore.setMarksId(quizMarksKey);
		quizScore.setQuiz(quiz);
		quizScore.setUser(user);
		quizScore.setMarks(totalMarksObtained);
		quizScore.setAttempted(attempted);
		quizScore.setCorrect(correctAnswers);
		quizScore.setTimestamp(Timestamp.from(Instant.now()));
		quizScoreRepository.save(quizScore);
		
		QuizScoreDTO quizScoreDTO=new QuizScoreDTO();
		quizScoreDTO.setAttempted(attempted);
		quizScoreDTO.setCorrect(correctAnswers);
		quizScoreDTO.setMarks(totalMarksObtained);
		
		
		
		if(totalMarksObtained/maxMarks*100>=passpercent)
			quizScoreDTO.setResult(true);
		else {
			
			quizScoreDTO.setResult(false);
		}
		return quizScoreDTO;
	}

	@Override
	public List<AdminQuizHistoryDTO> getQuizAttempts(Long qid) {
		Quiz quiz=new Quiz();
		quiz.setQid(qid);
		List<QuizScore> studentHistory=quizScoreRepository.findByQuiz(quiz);
		List<AdminQuizHistoryDTO> historyDTOs=studentHistory.stream().map((s)-> this.modelMapper.map(s, AdminQuizHistoryDTO.class)).collect(Collectors.toList());
		return historyDTOs;
	}

	@Override
	public List<StudentQuizHistoryDTO> getQuizAttemptsOfUser(Long id) {
		User user=new User();
		user.setId(id);
		List<QuizScore> studentHistory=quizScoreRepository.findByUser(user);
		List<StudentQuizHistoryDTO> historyDTOs=studentHistory.stream().map((s)-> this.modelMapper.map(s, StudentQuizHistoryDTO.class)).collect(Collectors.toList());
		return historyDTOs;
	}

	}


