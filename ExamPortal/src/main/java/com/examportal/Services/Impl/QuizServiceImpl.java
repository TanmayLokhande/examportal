package com.examportal.Services.Impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.Exceptions.ResourceNotFoundException;
import com.examportal.Repositories.QuestionRepo;
import com.examportal.Repositories.QuizRepo;
import com.examportal.Services.QuizService;
import com.examportal.entities.exam.Question;
import com.examportal.entities.exam.Quiz;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	private QuestionRepo questionRepo;
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepo.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzes() {
		// TODO Auto-generated method stub
		return new HashSet<>(this.quizRepo.findAll());
	}

	@Override
	public Quiz getQuiz(Long qId) {
		
		return this.quizRepo.findById(qId).orElseThrow(() -> new ResourceNotFoundException("Quiz", "Id"+qId, ""));
	}

	@Override
	public void deleteQuiz(Long quizId) {
		this.quizRepo.deleteById(quizId);
		System.out.println("deleted");
	}

	
}
