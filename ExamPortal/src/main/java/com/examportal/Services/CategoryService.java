package com.examportal.Services;

import java.util.Set;

import com.examportal.entities.exam.Category;

public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Set<Category> getCategories();
	
	public Category getCategory(Long cId);
	
	public void deleteCategory(Long cId);
	
}
