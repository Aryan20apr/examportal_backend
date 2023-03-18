package com.aryan.examportal_backend.services;

import java.util.List;

import com.aryan.examportal_backend.payload.CategoryDTO;

public interface CategoryService {

	public CategoryDTO addCategory(CategoryDTO categoryDTO,Long userid);
	public CategoryDTO updateCategory(CategoryDTO categoryDTO);
	public List<CategoryDTO> getCategories();
	public CategoryDTO getCategory(Long categoryId);
	public List<CategoryDTO> getCategoriesByUser(long userid);
	public void deleteCategory(Long categoryId);
	public List<CategoryDTO> getenrolledSubjects(long userid);
	public List<CategoryDTO> getUnenrolledSubjects(long userid);
}
