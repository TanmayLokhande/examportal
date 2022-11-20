package com.examportal.Repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.exam.Question;
import com.examportal.entities.exam.Quiz;

public interface QuestionRepo extends JpaRepository<Question, Long>{

	public Set<Question> findByQuiz(Quiz quiz);

}
