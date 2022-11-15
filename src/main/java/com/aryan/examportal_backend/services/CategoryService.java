package com.aryan.examportal_backend.services;

import java.util.List;
import java.util.Set;

import com.aryan.examportal_backend.model.Category;
import com.aryan.examportal_backend.payload.CategoryDTO;

public interface CategoryService {

	public CategoryDTO addCategory(CategoryDTO categoryDTO);
	public CategoryDTO updateCategory(CategoryDTO categoryDTO);
	public List<CategoryDTO> getCategories();
	public CategoryDTO getCategory(Long categoryId);
	
	public void deleteCategory(Long categoryId);
}
