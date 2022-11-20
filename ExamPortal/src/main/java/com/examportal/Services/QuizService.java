package com.examportal.Services;

import java.util.Set;

import com.examportal.entities.exam.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz) ;
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizzes();
	
	public Quiz getQuiz(Long qId);
	
	public void deleteQuiz(Long quizId);
}
