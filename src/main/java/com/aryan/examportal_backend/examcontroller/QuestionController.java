package com.aryan.examportal_backend.examcontroller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.examportal_backend.ApiResponse2;
import com.aryan.examportal_backend.payload.ApiResponse;
import com.aryan.examportal_backend.payload.QuestionDTO;
import com.aryan.examportal_backend.payload.QuizResultDTO;
import com.aryan.examportal_backend.services.QuestionService;


@RequestMapping("examportal/question")
@RestController
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("")
	
	public ResponseEntity<ApiResponse<QuestionDTO>> addQuestion(@RequestBody QuestionDTO question,@RequestParam Long qid){
		System.out.println("qid="+qid);
		QuestionDTO QuestionDTO=questionService.addQuestion(question,qid);
		ApiResponse<QuestionDTO> apiResponse=new ApiResponse<QuestionDTO>(QuestionDTO,HttpStatus.CREATED,true);
		return new ResponseEntity<ApiResponse<QuestionDTO>>(apiResponse,HttpStatus.CREATED);
	}
	
@PutMapping("/")
	
	public ResponseEntity<ApiResponse<QuestionDTO>> updateQuestion(@RequestBody QuestionDTO question/**,@RequestParam long qid*/){
	System.out.println("Question id is "+question.getQId()+" content is "+question.getContent());
		QuestionDTO QuestionDTO=questionService.updateQuestion(question);
		ApiResponse<QuestionDTO> apiResponse=new ApiResponse<>(QuestionDTO,HttpStatus.OK,true);
		return new ResponseEntity<ApiResponse<QuestionDTO>>(apiResponse,HttpStatus.OK);
	}
@GetMapping("/byid")
public ResponseEntity<ApiResponse<QuestionDTO>> getQuestion(@RequestParam Long questionId)
{
	QuestionDTO QuestionDTO=questionService.getQuestion(questionId);
	ApiResponse<QuestionDTO> apiResponse=new ApiResponse<>(QuestionDTO,HttpStatus.OK,true);
	return new ResponseEntity<ApiResponse<QuestionDTO>>(apiResponse,HttpStatus.OK);
}

@GetMapping("/allQuestions")
public ResponseEntity<ApiResponse<Set<QuestionDTO>>> getAllQuestions()
{
	Set<QuestionDTO> QuestionSet=questionService.getQuestions();
	ApiResponse<Set<QuestionDTO>> apiResponse=new ApiResponse<>(QuestionSet,HttpStatus.OK,true);
	return ResponseEntity.ok(apiResponse);
}

@DeleteMapping("/byid")
public ResponseEntity<?> deleteQuestion(@RequestParam Long id)
{
	questionService.deleteQuestion(id);
	ApiResponse<String> apiResponse=new ApiResponse<>("Question Deleted Successfully",HttpStatus.CREATED,true);
	return ResponseEntity.ok(apiResponse);
}
	//get all question of any quiz
	@GetMapping("/quizquestions")
	
	public ResponseEntity<ApiResponse<List<QuestionDTO>>> getQuizQuestions(@RequestParam Long quizId)
	{
		
		
		
		List<QuestionDTO> QuestionSet=questionService.getAllQuestionsOfQuiz(quizId);
		ApiResponse<List<QuestionDTO>> apiResponse=new ApiResponse<>(QuestionSet,HttpStatus.OK,true);
		return ResponseEntity.ok(apiResponse);
	}
	
		
//	@PostMapping("/submitquiz")
//	public ResponseEntity<?> submitQuiz
}
