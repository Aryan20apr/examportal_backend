package com.aryan.examportal_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aryan.examportal_backend.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	public List<Quiz> findByCategoryCid(Long cid);
	
	@Query("SELECT q from Quiz q where q.user.id=:userid and q.category.cid=:cid")
	public List<Quiz> findByUserIdOfCategory(Long userid,Long cid);
	
	@Query("SELECT q from Quiz q where q.active=true and q.category.cid=:cid and q.qid not in (select qs.quiz.qid from QuizScore qs where qs.user.id =:userid )")
	public List<Quiz> findActiveQuizesByCategoryCid(Long cid,Long userid);
	
	@Query("SELECT q from Quiz q where q.active=true")
	public List<Quiz> findActiveQuizes();
}
