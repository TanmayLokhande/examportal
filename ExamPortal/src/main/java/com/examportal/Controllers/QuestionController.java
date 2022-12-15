	package com.examportal.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.examportal.dto.QuestionDto;
import com.examportal.entities.exam.Question;
import com.examportal.entities.exam.Quiz;
import com.examportal.payLoads.ApiResponse;



@RestController
@RequestMapping("/question")
@CrossOrigin("*")
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
		List<Question> list = new ArrayList(questions);
		List<QuestionDto> questDto = new ArrayList<>();
		if(list.size()>Integer.parseInt(quiz.getNo_of_questions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNo_of_questions()+1));
		}
	
//		list.forEach((question) -> {
//			QuestionDto qDto = new QuestionDto();
//			qDto.setQuestionId(question.getQuestionId());
//			qDto.setContent(question.getContent());
//			qDto.setImage(question.getImage());
//			qDto.setOption1(question.getOption1());
//			qDto.setOption2(question.getOption2());
//			qDto.setOption3(question.getOption3());
//			qDto.setOption4(question.getOption4());
//			qDto.setGivenAnswer(question.getGivenAnswer());
//			qDto.setQuiz(question.getQuiz());
//			
//			questDto.add(qDto);
//			
//		});
		
		list.forEach((question) -> {
			question.setAnswer("");
		});
	
		java.util.Collections.shuffle(list);
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@GetMapping("/quiz/all/{quizId}")
	public ResponseEntity<?> getQuestionsByQuizAdmin(@PathVariable Long quizId){
		Quiz quiz = this.quizService.getQuiz(quizId);
		Set<Question> list = quiz.getQuestions();
		return new ResponseEntity<>(list,HttpStatus.OK);
		
	}
	
	@GetMapping("/{questionId}")
	public ResponseEntity<Question> getQuestions(@PathVariable("questionId") Long questionId){
		return new ResponseEntity<Question>(this.questionService.getQuestion(questionId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{questionId}")
	public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable("questionId") Long questionId){
		this.questionService.deleteQuestion(questionId);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Question deleted successfully!!");
		apiResponse.setStatus(true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){

		 double marksGot = 0;
		 int correctAnswers=0;
		 int attempted =0;
		 
		 for(Question question:questions) {
			 Question question2 = this.questionService.getQuestion(question.getQuestionId());
			
			if(question2.getAnswer().equals(question.getGivenAnswer())) {
				correctAnswers++;
				
				double marksOfOneQuestion = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
			    marksGot+=marksOfOneQuestion;
			    marksGot = Math.round(marksGot*100)/100;
			}
			
			if(question.getGivenAnswer() != null ) {
				attempted++;
			}
			
		 }
		 Map<String, Object> of = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
	
		return ResponseEntity.ok(of);
	}
	
	

}
