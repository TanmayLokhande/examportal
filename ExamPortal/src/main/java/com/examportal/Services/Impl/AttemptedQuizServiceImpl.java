package com.examportal.Services.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.Repositories.AttemptedQuizRepo;
import com.examportal.Repositories.UserRepo;
import com.examportal.Services.AttemptedQuizService;
import com.examportal.Services.UserService;
import com.examportal.dto.requestDto;
import com.examportal.entities.User;
import com.examportal.entities.exam.AttemptedQuiz;

@Service
public class AttemptedQuizServiceImpl implements AttemptedQuizService{

	@Autowired
	private AttemptedQuizRepo attemptedQuizRepo;
	
	@Autowired
	private UserService userService;
	
	@Override
	public AttemptedQuiz saveInfo(requestDto requestDto) {
		String total = requestDto.getTotalMarks();
		User user = requestDto.getUser();
		
		AttemptedQuiz attemptedQuiz = new AttemptedQuiz();
		attemptedQuiz.setTotalMarks(total);
		attemptedQuiz.setQuizTitle(requestDto.getQuizTitle());
		attemptedQuiz.setCorrectAnswers(requestDto.getCorrectAnswers());
		attemptedQuiz.setAttemptesQuestions(requestDto.getAttemptesQuestions());
		attemptedQuiz.setNoOfQuestions(requestDto.getNoOfQuestions());
		
		String username = requestDto.getUser().getUsername();
		
		User user2 = this.userService.getUser(username);
		
		attemptedQuiz.setUser(user2);
		
		AttemptedQuiz savedQuiz = this.attemptedQuizRepo.save(attemptedQuiz);
		
		return savedQuiz;
	}

	@Override
	public List<AttemptedQuiz> getQuizzes(User user) {
		List<AttemptedQuiz> allAttemptedQuizzes = this.attemptedQuizRepo.findByuser(user);
		return allAttemptedQuizzes;
	}

	
	
}
