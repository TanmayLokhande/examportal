package com.examportal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.exam.Category;
import com.examportal.entities.exam.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Long>{

	public List<Quiz> findBycategory(Category category);
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category category,Boolean b);
	
}
