package com.examportal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.exam.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Long>{

}
