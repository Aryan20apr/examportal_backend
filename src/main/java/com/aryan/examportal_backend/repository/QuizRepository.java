package com.aryan.examportal_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aryan.examportal_backend.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	public List<Quiz> findByCategoryCid(Long cid);
}
