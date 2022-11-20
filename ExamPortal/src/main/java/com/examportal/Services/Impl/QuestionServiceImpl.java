package com.examportal.Services.Impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.Exceptions.ResourceNotFoundException;
import com.examportal.Repositories.QuestionRepo;
import com.examportal.Services.QuestionService;
import com.examportal.entities.exam.Question;
import com.examportal.entities.exam.Quiz;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepo questionRepo;

	@Override
	public Question addQuestion(Question question) {
		return this.questionRepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return this.questionRepo.save(question);
	}

	@Override
	public Set<Question> getAllQuestions() {
		return new HashSet<>(this.questionRepo.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		return this.questionRepo.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("Question", "Id"+questionId, ""));
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		
		return this.questionRepo.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long questionId) {
		Question question = this.questionRepo.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("Question", "Id"+questionId, ""));
		this.questionRepo.delete(question);
	}
	
	
	
}
