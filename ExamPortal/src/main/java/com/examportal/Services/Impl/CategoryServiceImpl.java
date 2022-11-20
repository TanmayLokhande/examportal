package com.examportal.Services.Impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.Exceptions.ResourceNotFoundException;
import com.examportal.Repositories.CategoryRepo;
import com.examportal.Services.CategoryService;
import com.examportal.entities.exam.Category;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public Category addCategory(Category category) {
		
		return this.categoryRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		
		return this.categoryRepo.save(category);
	}

	@Override
	public Set<Category> getCategories() {
	
		return new LinkedHashSet<>(this.categoryRepo.findAll());
	}

	@Override
	public Category getCategory(Long cId) {
		
		return this.categoryRepo.findById(cId).orElseThrow(() ->  new ResourceNotFoundException("Category", "Id"+cId,""));
	}

	@Override
	public void deleteCategory(Long cId) {
		Category category = this.categoryRepo.findById(cId).orElseThrow(()->new ResourceNotFoundException("Category", "Id"+cId, ""));
		this.categoryRepo.delete(category);
		
	}

}
