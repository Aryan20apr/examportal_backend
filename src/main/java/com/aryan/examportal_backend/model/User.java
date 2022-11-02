package com.aryan.examportal_backend.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User implements UserDetails //Make User as the implementation class of UserDetail interface
{
//Wherever Spring requires UserDetails, pass the User class instance
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private boolean enabled=true;
	private String profileImage;
	
	//One user can have many roles. Therefore we use List or Set for those roles
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "user")//If user is fetched, its roles are also fetched
	//If mapped by is not written, another table is created
	@JsonIgnore//Prevent Circular Dependency
	private Set<UserRole> userRoles=new HashSet<>();
	
	public User(){
	
}
	
	
	public User(Long id, String username, String password, String firstName, String lastName, String email,
			String phone, boolean enabled, String profileImage) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.profileImage = profileImage;
	}



	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}


	public Set<UserRole> getUserRoles() {
		System.out.println("User Roles:"+userRoles);
		return userRoles;
	}


	public void setUserRoles(Set<UserRole> userRoles) {
		System.out.println("UserRoles:"+userRoles);
		this.userRoles = userRoles;
	}
	/***********************************************************************************/

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//Authority is role- admin or normal
		
		Set<Authority> set=new HashSet<>();
		this.userRoles.forEach(userRole->{
			set.add(new Authority(userRole.getRole().getRoleName()));
		});
		return null;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;//false;
	}


	@Override
	public boolean isAccountNonLocked() {
		//
		return true;//false;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;//false;
	}
	
	
}
