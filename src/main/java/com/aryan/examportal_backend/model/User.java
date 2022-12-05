package com.aryan.examportal_backend.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails // Make User as the implementation class of UserDetail interface
{
//Wherever Spring requires UserDetails, pass the User class instance

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nickname;
	private String password;
	private String firstName;
	private String lastName;

	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String phone;
	private boolean enabled = true;
	private String profileImage;

	// One user can have many roles. Therefore we use List or Set for those roles
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user") // If user is fetched, its roles
																						// are also fetched
	// If mapped by is not written, another table is created
	@JsonIgnore // Prevent Circular Dependency
	private Set<UserRole> userRoles = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@JsonIgnore
	private List<Quiz> quizes = new ArrayList<>();

	
	 @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy ="user")
	 @JsonIgnore 
	 private List<Category> cat_created=new ArrayList<>();
	 
	
	 
	 @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)//When use is created, role also created 
		@JoinTable(name = "students_enrolled", joinColumns = @JoinColumn(name = "userid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "categoryid", referencedColumnName = "cid"))
		private List<Category> subjectsEnrolled = new ArrayList<>();
	 
	public User() {

	}

	public User(Long id, String username, String password, String firstname, String lastName, String email,
			String phone, boolean enabled, String profileImage) {
		super();
		this.id = id;
		this.nickname = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.profileImage = profileImage;
	}

//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPhone() {
//		return phone;
//	}
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//	public boolean isEnabled() {
//		return enabled;
//	}
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}
//	public String getProfileImage() {
//		return profileImage;
//	}
//	public void setProfileImage(String profileImage) {
//		this.profileImage = profileImage;
//	}
//
//
//	public Set<UserRole> getUserRoles() {
//		System.out.println("User Roles:"+userRoles);
//		return userRoles;
//	}
//
//
//	public void setUserRoles(Set<UserRole> userRoles) {
//		System.out.println("UserRoles:"+userRoles);
//		this.userRoles = userRoles;
//	}
	/***********************************************************************************/

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Authority is role- admin or normal

		Set<Authority> set = new HashSet<>();
		this.userRoles.forEach(userRole -> {
			set.add(new Authority(userRole.getRole().getRoleName()));
		});
		return set;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;// false;
	}

	@Override
	public boolean isAccountNonLocked() {
		//
		return true;// false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;// false;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

}
