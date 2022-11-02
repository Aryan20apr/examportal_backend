package com.aryan.examportal_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aryan.examportal_backend.model.UserRole;

public interface UserRoleRepsoitory extends JpaRepository<UserRole, Long> {

}
