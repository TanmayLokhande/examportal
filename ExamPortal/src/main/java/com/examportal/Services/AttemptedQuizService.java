package com.examportal.Services;

import java.util.List;
import java.util.Set;

import com.examportal.dto.requestDto;
import com.examportal.entities.User;
import com.examportal.entities.exam.AttemptedQuiz;

public interface AttemptedQuizService {

	public AttemptedQuiz saveInfo(requestDto requestDto);
	
	public List<AttemptedQuiz> getQuizzes(User user);
	
}
