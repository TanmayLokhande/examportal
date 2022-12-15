	package com.examportal.Controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.Services.QuizService;
import com.examportal.entities.exam.Category;
import com.examportal.entities.exam.Quiz;
import com.examportal.payLoads.ApiResponse;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
		Quiz quiz2 = this.quizService.addQuiz(quiz);
		return new ResponseEntity<Quiz>(quiz2,HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
		Quiz updateQuiz = this.quizService.updateQuiz(quiz);
		return new ResponseEntity<Quiz>(updateQuiz,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Set<Quiz>> getAllQuizzes(){
		return new ResponseEntity<Set<Quiz>>(this.quizService.getQuizzes(),HttpStatus.OK);
	}
	
	@GetMapping("/{quizId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") Long quizId){
		return new ResponseEntity<Quiz>(this.quizService.getQuiz(quizId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{quizId}")
	public ResponseEntity<ApiResponse> deleteQuiz(@PathVariable("quizId") Long quizId){
		this.quizService.deleteQuiz(quizId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Quiz deleted successfully!!");
		apiResponse.setStatus(true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/category/{catId}")
	public ResponseEntity<List<Quiz>> getQuizzesOfCategory(@PathVariable("catId") Long catId){
		Category category = new Category();
		category.setCId(catId);
		 List<Quiz> quizzes = this.quizService.getQuizzesByCategory(category);
		 return new ResponseEntity<List<Quiz>>(quizzes,HttpStatus.OK);
	}	
	
	@GetMapping("/active")
	public ResponseEntity<List<Quiz>> getActiveQuizzes(){
		List<Quiz> quizzes = this.quizService.getActiveQuizzes();
		 return new ResponseEntity<List<Quiz>>(quizzes,HttpStatus.OK);
	}
	
	@GetMapping("/category/active/{catId}")
	public ResponseEntity<List<Quiz>> getActiveQuizzesByCategory(@PathVariable("catId") Long catId){
		Category category = new Category();
		category.setCId(catId);
		List<Quiz> quizzes = this.quizService.getActiveQuizzesByCategory(category);
		 return new ResponseEntity<List<Quiz>>(quizzes,HttpStatus.OK);
	}
	
}
