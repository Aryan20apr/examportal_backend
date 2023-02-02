package com.aryan.examportal_backend.examcontroller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.examportal_backend.exceptions.NotFoundException;
import com.aryan.examportal_backend.payload.ApiResponse;
import com.aryan.examportal_backend.payload.CategoryDTO;
import com.aryan.examportal_backend.payload.QuizDTO;
import com.aryan.examportal_backend.services.QuizService;

@RestController
@RequestMapping("examportal/quiz")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addQuiz")
	
	public ResponseEntity<ApiResponse<QuizDTO>> addQuiz(@RequestBody QuizDTO quiz,@RequestParam int userid)
	{
		QuizDTO quizDTO=quizService.addQuiz(quiz,userid);
		System.out.println("Quiz Duration is "+quiz.getQuizDuration());
		ApiResponse<QuizDTO> apiResponse=new ApiResponse<>(quizDTO,HttpStatus.CREATED,true);
		return new ResponseEntity<ApiResponse<QuizDTO>>(apiResponse,HttpStatus.CREATED);
	}
	
@PutMapping("/update")
@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<QuizDTO>> updateQuiz(@RequestBody QuizDTO quiz,@RequestParam long userid)
	{
		QuizDTO quizDTO=quizService.updateQuiz(quiz,userid);
		ApiResponse<QuizDTO> apiResponse=new ApiResponse<>(quizDTO,HttpStatus.OK,true);
		return new ResponseEntity<ApiResponse<QuizDTO>>(apiResponse,HttpStatus.OK);
	}
@GetMapping("/get")
public ResponseEntity<ApiResponse<QuizDTO>> getQuiz(@RequestParam Long quizId)
{
	QuizDTO quizDTO=quizService.getQuiz(quizId);
	ApiResponse<QuizDTO> apiResponse=new ApiResponse<>(quizDTO,HttpStatus.OK,true);
	return new ResponseEntity<ApiResponse<QuizDTO>>(apiResponse,HttpStatus.OK);
}

@GetMapping("/allquizes")
public ResponseEntity<ApiResponse<Set<QuizDTO>>> getAllQuizes()
{
	Set<QuizDTO> quizSet=quizService.getQuizzes();
	ApiResponse<Set<QuizDTO>> apiResponse=new ApiResponse<>(quizSet,HttpStatus.OK,true);
	return ResponseEntity.ok(apiResponse);
}
@GetMapping("/allactivequizes")
public ResponseEntity<ApiResponse<List<QuizDTO>>> getAllActiveQuizes()
{
	List<QuizDTO> quizSet=quizService.getActiveQuizes();
	ApiResponse<List<QuizDTO>> apiResponse=new ApiResponse<>(quizSet,HttpStatus.OK,true);
	return ResponseEntity.ok(apiResponse);
}

@DeleteMapping("/delete")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public ResponseEntity<?> deleteQuiz(@RequestParam Long qid) throws NotFoundException
{
	
	quizService.deleteQuiz(qid);
	
	ApiResponse<String> apiResponse=new ApiResponse<>("Quiz Deleted Successfully",HttpStatus.OK,true);
	return ResponseEntity.ok(apiResponse);
}


@GetMapping("/getbycategory")
public ResponseEntity<ApiResponse<List<QuizDTO>>> getQuizByCategory(@RequestParam Long cid)
{
	List<QuizDTO> quizzes=quizService.getQuizzesByCategory(cid);
	ApiResponse<List<QuizDTO>> apiResponse=new ApiResponse<>(quizzes,HttpStatus.OK,true);
	return new ResponseEntity<ApiResponse<List<QuizDTO>>>(apiResponse,HttpStatus.OK);
}

@GetMapping("/getactivebycategory")
public ResponseEntity<ApiResponse<List<QuizDTO>>> getActiveQuizByCategory(@RequestParam Long cid,@RequestParam Long userid)
{
	List<QuizDTO> quizzes=quizService.getActiveQuizesByCategory(cid,userid);
	ApiResponse<List<QuizDTO>> apiResponse=new ApiResponse<>(quizzes,HttpStatus.OK,true);
	return new ResponseEntity<ApiResponse<List<QuizDTO>>>(apiResponse,HttpStatus.OK);
}

@GetMapping("/getbyuser")
public ResponseEntity<ApiResponse<List<QuizDTO>>> getQuizByUser(@RequestParam Long userid,@RequestParam Long cid )
{
	List<QuizDTO> quizzes=quizService.getQuizzesByUser(userid,cid);
	ApiResponse<List<QuizDTO>> apiResponse=new ApiResponse<>(quizzes,HttpStatus.OK,true);
	return new ResponseEntity<ApiResponse<List<QuizDTO>>>(apiResponse,HttpStatus.OK);
}


}
