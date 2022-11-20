package com.examportal.Controllers;

import java.util.ArrayList;
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

import com.examportal.Services.QuestionService;
import com.examportal.Services.QuizService;
import com.examportal.entities.exam.Question;
import com.examportal.entities.exam.Quiz;
import com.examportal.payLoads.ApiResponse;



@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		return new ResponseEntity<Question>(this.questionService.addQuestion(question),HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		return new ResponseEntity<Question>(this.questionService.updateQuestion(question),HttpStatus.OK);
	}
	
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestionsByQuiz(@PathVariable Long quizId){
		Quiz quiz = this.quizService.getQuiz(quizId);
		Set<Question> questions = quiz.getQuestions();
		List list = new ArrayList(questions);
		if(list.size()>Integer.parseInt(quiz.getNo_of_questions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNo_of_questions()+1));
		}
	
		java.util.Collections.shuffle(list);
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@GetMapping("/{quizId}")
	public ResponseEntity<Question> getQuestions(@PathVariable("quizId") Long quizId){
		return new ResponseEntity<Question>(this.questionService.getQuestion(quizId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{quizId}")
	public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable("quizId") Long quizId){
		this.questionService.deleteQuestion(quizId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Question deleted successfully!!");
		apiResponse.setStatus(true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	

}
