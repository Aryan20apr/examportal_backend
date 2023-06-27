package com.aryan.examportal_backend.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long cid;
	
	String title;
	
	String description;
	
	@OneToMany(mappedBy = "category",fetch=FetchType.LAZY,cascade = CascadeType.ALL)//IF THIS IS EAGER, WHEN QUIZ IS DELETED, IT IS NOT REMOVED FROM DATABASE, AS IT GETS SAVED AGAIN BY HIBERNATE
	//@JsonIgnore//One category can have many quizzes
	private Set<Quiz> quizes=new LinkedHashSet<>();//Use linkedhashset to maintain the order

	@ManyToOne(fetch=FetchType.LAZY)//IF THIS IS EAGER, WHEN QUIZ IS DELETED, IT IS NOT REMOVED FROM DATABASE, AS IT GETS SAVED AGAIN BY HIBERNATE
	@JsonIgnore//One category can have many quizzes
	private User user;
	
	 @ManyToMany(fetch = FetchType.LAZY,mappedBy = "subjectsEnrolled")
	 @JsonIgnore 
	 private List<User> usersEnrolled=new ArrayList<>();

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
	
	/*
	 * @ManyToOne private User user;
	 */

}
