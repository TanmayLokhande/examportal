package com.blog.Services;

import java.util.List;

import com.blog.Payloads.CategoryDto;

public interface CategoryServices {

	CategoryDto createCategory( CategoryDto categoryDto );
	
	CategoryDto updateCategory( CategoryDto categoryDto,Integer categoryId );
	
	void deleteCategory( Integer categoryId );
	
	CategoryDto getCategory( Integer categoryId );
	
	List<CategoryDto> getCategories();
	
	
}
