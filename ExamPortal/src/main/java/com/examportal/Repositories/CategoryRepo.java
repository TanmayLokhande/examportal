package com.examportal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.exam.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

}
