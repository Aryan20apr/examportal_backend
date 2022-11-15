package com.aryan.examportal_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aryan.examportal_backend.model.Category;
import com.aryan.examportal_backend.payload.CategoryDTO;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	

}
