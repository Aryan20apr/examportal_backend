package com.aryan.examportal_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aryan.examportal_backend.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	List<Category>findByUserId(Long userid);

	@Query("Select c from Category c where c.cid not in(Select c.cid from c.usersEnrolled p where p.id=:userid)")
	List<Category>findUnenrolledByUser(long userid);
	
}
