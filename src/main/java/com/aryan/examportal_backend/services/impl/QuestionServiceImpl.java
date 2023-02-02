package com.aryan.examportal_backend.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aryan.examportal_backend.model.Question;
import com.aryan.examportal_backend.model.Quiz;
import com.aryan.examportal_backend.payload.QuestionDTO;
import com.aryan.examportal_backend.payload.QuizResultDTO;
import com.aryan.examportal_backend.repository.QuestionRepository;
import com.aryan.examportal_backend.repository.QuizRepository;
import com.aryan.examportal_backend.services.QuestionService;



@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuizRepository quizRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public QuestionDTO addQuestion(QuestionDTO question,Long qid) {
		
		Question question1=modelMapper.map(question, Question.class);
		Quiz q=quizRepository.findById(qid).get();
		question1.setQuiz(q);
		Question savedQuestion=questionRepository.save(question1);
		return modelMapper.map(savedQuestion,QuestionDTO.class);
	}

	@Override
	public QuestionDTO updateQuestion(QuestionDTO question/* ,long qid */) {
		Question question1=modelMapper.map(question, Question.class);
		System.out.println("Question is is"+question.getQId());
		Question q=questionRepository.findById(question.getQId()).get();
		question1.setQuiz(q.getQuiz());
		Question savedQuestion=questionRepository.save(question1);
		return modelMapper.map(savedQuestion,QuestionDTO.class);
		
	}

	@Override
	public Set<QuestionDTO> getQuestions() {
		
		List<Question> questions=questionRepository.findAll();
		 Set<QuestionDTO> questionDTOs= questions.stream().map((cat)-> this.modelMapper.map(cat, QuestionDTO.class)).collect(Collectors.toSet());
		 return questionDTOs;
	}

	@Override
	public QuestionDTO getQuestion(Long id) {
		
		Optional<Question> question=questionRepository.findById(id);
		return modelMapper.map(question, QuestionDTO.class);
	}

	@Override
	public void deleteQuestion(Long questionId) {
		questionRepository.deleteById(questionId);

	}

	@Override
	public List<QuestionDTO> getQuestionsOfQuiz(Long quizId) {
		Optional<Quiz> q =quizRepository.findById(quizId);
		Quiz quiz=q.get();
		int numberOfQuestions=quiz.getNumberOfQuestions();
		List<Question> questions=questionRepository.findByQuiz(q);
		List<Question> questionList=new ArrayList<>(questions);
		
		//We will send only that much questions as the number of questions in the quiz
		List<QuestionDTO> questionDTOs;
		if(questionList.size()>numberOfQuestions)
		{
			questionList=questionList.subList(0, numberOfQuestions+1);
			 
		}
		questionDTOs= questionList.stream().map((cat)-> this.modelMapper.map(cat, QuestionDTO.class)).collect(Collectors.toList());
		Collections.shuffle(questionDTOs);//Shuffle the order of questions everytime the function is called
		return questionDTOs;
	}
	@Override
	public List<QuestionDTO> getAllQuestionsOfQuiz(Long quizId) {
		Optional<Quiz> q =quizRepository.findById(quizId);
		//Quiz quiz=q.get();
		//int numberOfQuestions=quiz.getNumberOfQuestions();
		//Set<Question> questions=quiz.getQuestions();
		List<Question> questionList=questionRepository.findByQuiz(q);
		
		//We will send only that much questions as the number of questions in the quiz
		/*
		 * List<QuestionDTO> questionDTOs; if(questionList.size()>numberOfQuestions) {
		 * questionList=questionList.subList(0, numberOfQuestions+1);
		 * 
		 * }
		 */
		List<QuestionDTO> questionDTOs= questionList.stream().map((cat)-> this.modelMapper.map(cat, QuestionDTO.class)).collect(Collectors.toList());
		Collections.shuffle(questionDTOs);//Shuffle the order of questions everytime the function is called
		return questionDTOs;
	}




}
