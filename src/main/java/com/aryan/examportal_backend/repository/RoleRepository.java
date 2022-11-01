package com.aryan.examportal_backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.aryan.examportal_backend.model.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
