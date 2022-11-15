package com.aryan.examportal_backend.services.impl;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aryan.examportal_backend.model.Category;
import com.aryan.examportal_backend.payload.CategoryDTO;
import com.aryan.examportal_backend.repository.CategoryRepository;
import com.aryan.examportal_backend.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
	 Category category=modelMapper.map(categoryDTO,Category.class);
		Category savedCategory= categoryRepository.save(category);
		return modelMapper.map(savedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
		
		Category category=modelMapper.map(categoryDTO,Category.class);
		Category savedCategory= categoryRepository.save(category);
		return modelMapper.map(savedCategory, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getCategories() {
		
		List<Category> categories=categoryRepository.findAll();
		 List<CategoryDTO> categoryDTOs= categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		 return categoryDTOs;
	}

	@Override
	public CategoryDTO getCategory(Long categoryId) {
		Optional<Category> category=categoryRepository.findById(categoryId);
		return modelMapper.map(category, CategoryDTO.class);
		
	}

	@Override
	public void deleteCategory(Long categoryId) {
		
		
		categoryRepository.deleteById(categoryId);

	}

	

}
