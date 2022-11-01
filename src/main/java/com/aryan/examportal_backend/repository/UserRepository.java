package com.aryan.examportal_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aryan.examportal_backend.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
