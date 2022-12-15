package com.examportal.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examportal.Services.AttemptedQuizService;
import com.examportal.Services.UserService;
import com.examportal.dto.requestDto;
import com.examportal.entities.User;
import com.examportal.entities.exam.AttemptedQuiz;

@RestController
@RequestMapping("/save-result")
@CrossOrigin("*")
public class AttemptedQuizController {
	
	@Autowired
	private AttemptedQuizService attemptedQuizService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<AttemptedQuiz> saveQuiz(@RequestBody requestDto obj) {
		AttemptedQuiz savedInfo = this.attemptedQuizService.saveInfo(obj);
		return new ResponseEntity<AttemptedQuiz>(savedInfo,HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<AttemptedQuiz>> getAllQuizzes(@PathVariable("userId") Integer userId) {
		User user = this.userService.getUserById(userId);
		List<AttemptedQuiz> quizzes = this.attemptedQuizService.getQuizzes(user);	
		return new ResponseEntity<List<AttemptedQuiz>>(quizzes,HttpStatus.OK);
	}
}
