package com.blog.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Entities.Category;
import com.blog.Exceptions.ResourceNotFoundException;
import com.blog.Payloads.CategoryDto;
import com.blog.Repositories.CategoryRepo;
import com.blog.Services.CategoryServices;

@Service
public class CategoryServiceImpl implements CategoryServices{

	@Autowired
	private CategoryRepo categoRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat =  this.categoRepo.save(category);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category category = this.categoRepo.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category", "Id", categoryId) );
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = this.categoRepo.save(category);
		
		return this.modelMapper.map(updatedCategory,CategoryDto.class );
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		this.categoRepo.delete(category);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category category = this.categoRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> categories =  this.categoRepo.findAll();
		
		List<CategoryDto> catDto = categories.stream().map( category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList()); 
		
		return catDto;
	}

	
}
