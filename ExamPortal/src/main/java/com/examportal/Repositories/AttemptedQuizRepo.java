package com.examportal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.User;
import com.examportal.entities.exam.AttemptedQuiz;

public interface AttemptedQuizRepo extends JpaRepository<AttemptedQuiz, Integer>{

	public List<AttemptedQuiz> findByuser(User user);
		
}
