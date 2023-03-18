package com.aryan.examportal_backend.examcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.examportal_backend.ApiResponse2;
import com.aryan.examportal_backend.payload.AdminQuizHistoryDTO;
import com.aryan.examportal_backend.payload.QuestionDTO;
import com.aryan.examportal_backend.payload.QuizScoreDTO;
import com.aryan.examportal_backend.payload.StudentQuizHistoryDTO;
import com.aryan.examportal_backend.services.QuizScoreService;


@RestController
@RequestMapping("/examportal/quizscore")
public class QuizScoreController {
	
	@Autowired
	private QuizScoreService quizScoreService;
	//evaluating the quiz
		@PostMapping("/evalquiz")
		public ResponseEntity<ApiResponse2<QuizScoreDTO>> evalQuiz(@RequestBody List<QuestionDTO> questions,@RequestParam Long userid,@RequestParam Long quizid)
		{
			System.out.println(questions);
			
		 QuizScoreDTO quizScoreDTO=quizScoreService.evaluateQuiz(questions,userid,quizid);
			
		 ApiResponse2<QuizScoreDTO> apiResponse2=new ApiResponse2<>();
		 apiResponse2.setData(quizScoreDTO);
		 apiResponse2.setMessage("Quiz Evaluated");
		 apiResponse2.setSuccess(true);
			return ResponseEntity.ok(apiResponse2);
		}
		
		@GetMapping("/attempts")
		public ResponseEntity<ApiResponse2<List<AdminQuizHistoryDTO>>> getQuizAttempHistory(@RequestParam Long qid)
		{
			List<AdminQuizHistoryDTO> history=quizScoreService.getQuizAttempts(qid);
			 ApiResponse2<List<AdminQuizHistoryDTO>> apiResponse2=new ApiResponse2<>();
			 apiResponse2.setData(history);
			 apiResponse2.setMessage("Quiz History");
			 apiResponse2.setSuccess(true);
			 return ResponseEntity.ok(apiResponse2);
		}
		@GetMapping("/user-attempts")
		public ResponseEntity<ApiResponse2<List<StudentQuizHistoryDTO>>> getQuizAttempHistoryOfUser(@RequestParam Long id)
		{
			List<StudentQuizHistoryDTO> history=quizScoreService.getQuizAttemptsOfUser(id);
			 ApiResponse2<List<StudentQuizHistoryDTO>> apiResponse2=new ApiResponse2<>();
			 apiResponse2.setData(history);
			 apiResponse2.setMessage("Quiz History");
			 apiResponse2.setSuccess(true);
			 return ResponseEntity.ok(apiResponse2);
		}

	
	

}
