package com.aryan.examportal_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aryan.examportal_backend.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByNickname(String nickname);
	
	public User  findByEmail(String email);
	
	public User findByPhone(String phone);
	
	@Query("SELECT p  from User p JOIN p.subjectsEnrolled q WHERE q.cid=:cid")
	public List<User> findBySubjectsEnrolled(long cid); 
	
	
	
}
