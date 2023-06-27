package com.aryan.examportal_backend.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aryan.examportal_backend.Constants.PasswordChangeStatus;
import com.aryan.examportal_backend.exceptions.UserAlreadyExistException;
import com.aryan.examportal_backend.model.Category;
import com.aryan.examportal_backend.model.Role;
import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.model.UserRole;
import com.aryan.examportal_backend.payload.CategoryDTO;
import com.aryan.examportal_backend.payload.PasswordChangeDTO;
import com.aryan.examportal_backend.payload.UserDTO;
import com.aryan.examportal_backend.repository.CategoryRepository;
import com.aryan.examportal_backend.repository.RoleRepository;
import com.aryan.examportal_backend.repository.UserRepository;
import com.aryan.examportal_backend.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;//Object of implementation class of UserRepository
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO createUser (UserDTO userdto)throws Exception {
		
		User user=	modelMapper.map(userdto, User.class);
		User exisitingUser=this.userRepository.findByNickname(user.getNickname());
		
		User emailUser=this.userRepository.findByEmail(user.getEmail());
		User phoneUser=this.userRepository.findByPhone(user.getPhone());
		//System.out.println("emailUser="+emailUser.getEmail()+" phone="+emailUser.getPhone());
		//System.out.println("phoneUser="+phoneUser.getEmail()+" phone="+phoneUser.getPhone());
		if(exisitingUser!=null) {
			System.out.println("User is already present");
			
			throw new UserAlreadyExistException(userdto,"User with this username already exist",false);
		}
		else if(phoneUser!=null)
		{
			System.out.println("phoneUser="+phoneUser.getEmail()+" phone="+phoneUser.getPhone());
			throw new UserAlreadyExistException(userdto,"User with this phone already exist",false);
		}
		else if(emailUser!=null)
		{System.out.println("emailUser="+emailUser.getEmail()+" phone="+emailUser.getPhone());
			throw new UserAlreadyExistException(userdto,"User with this email already exist",false);
		}
		else {
			Set<UserRole> userRoles=new HashSet<>();
			Role role=new Role();
			role.setRoleId(45L);
			role.setRoleName("NORMAL");//Every user who sign up will have a NORMAL ROLE
			
			UserRole userRole=new UserRole();
			userRole.setUser(user);
			userRole.setRole(role);
			userRoles.add(userRole );
			for(UserRole ur:userRoles)
			{
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			//encoded the password
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			exisitingUser=this.userRepository.save(user);
			
			

			

			
			
			
		}
		
		return modelMapper.map(exisitingUser, UserDTO.class);
	}

	@Override
	public UserDTO getUserByNickname(String username) {
		User user=userRepository.findByNickname(username);
		return modelMapper.map(user, UserDTO.class);
		
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	public UserDTO updateUser(UserDTO user) {
		
			
			User existingUser=userRepository.findByEmail(user.getEmail());
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setNickname(user.getNickname());
			existingUser.setPhone(user.getPhone());
			existingUser.setProfileImage(user.getProfileImage());
			userRepository.save(existingUser);
			
			
			return modelMapper.map(existingUser,UserDTO.class);
		
	}
	
	 @Override
	    public PasswordChangeStatus changePassword(PasswordChangeDTO passwordChangeDTO) {
	        
	       User user= userRepository.findByEmail(passwordChangeDTO.getEmail());
	        
	        
	        if(user==null)
	        {
	            return PasswordChangeStatus.USER_DOES_NOT_EXIST;
	        }
	        String password=user.getPassword();
	       
	      
	       
	        if(passwordEncoder.matches(passwordChangeDTO.getPassword(), password))
	        {
	            System.out.println("Password are same");
	            String encodedPassword=passwordEncoder.encode(passwordChangeDTO.getNewpassword());
	            user.setPassword(encodedPassword);
	            User updatedUser=userRepository.save(user);
	            return PasswordChangeStatus.PASSWORD_CHANGED;
	        }
	        else
	        {
	            return PasswordChangeStatus.PASSWORD_INCORRECT;
	        }
	        
	        
	      
	    }
	    @Override
	    public PasswordChangeStatus forgotPassword(PasswordChangeDTO passwordChangeDTO) {
	        
	       User user= userRepository.findByEmail(passwordChangeDTO.getEmail());
	        
	        
	        //System.out.println(user.getEmail());
	        if(user==null)
	        {
	            return PasswordChangeStatus.USER_DOES_NOT_EXIST;
	        }
	        
	       
	      
	       
	       
	        String encodedPassword=passwordEncoder.encode(passwordChangeDTO.getNewpassword());
	        user.setPassword(encodedPassword);
	            User updatedUser=userRepository.save(user);
	            return PasswordChangeStatus.PASSWORD_CHANGED;
	        
	       
	        
	        
	      
	    }

	    @Override
		public UserDTO registerNewUser(UserDTO userdto) {
			
	    	User user=	modelMapper.map(userdto, User.class);
			User exisitingUser=this.userRepository.findByNickname(user.getNickname());
			
			System.out.println("User is "+user.getFirstName()+" "+user.getLastName());
			User emailUser=this.userRepository.findByEmail(user.getEmail());
			User phoneUser=this.userRepository.findByEmail(user.getEmail());
			if(exisitingUser!=null) {
				System.out.println("User is already present");
				throw new UserAlreadyExistException(userdto,"User with this username already exist",false);
			}
			else if(phoneUser!=null)
			{
				throw new UserAlreadyExistException(userdto,"User with this phone already exist",false);
			}
			else if(emailUser!=null)
			{
				throw new UserAlreadyExistException(userdto,"User with this email already exist",false);
			}
			else {
				Set<UserRole> userRoles=new HashSet<>();
				Role role=new Role();
				role.setRoleId(45L);
				role.setRoleName("ROLE_NORMAL");//Every user who sign up will have a NORMAL ROLE
				
				UserRole userRole=new UserRole();
				userRole.setUser(user);
				userRole.setRole(role);
				userRoles.add(userRole );
				for(UserRole ur:userRoles)
				{
					roleRepository.save(ur.getRole());
				}
				user.getUserRoles().addAll(userRoles);
				//encoded the password
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				exisitingUser=this.userRepository.save(user);
		}

			return modelMapper.map(exisitingUser, UserDTO.class);

	    }
	    @Override
		public UserDTO registerAdmin(UserDTO userdto) {
			
	    	User user=	modelMapper.map(userdto, User.class);
			User exisitingUser=this.userRepository.findByNickname(user.getNickname());
			
			System.out.println("User is "+user.getFirstName()+" "+user.getLastName());
			User emailUser=this.userRepository.findByEmail(user.getEmail());
			User phoneUser=this.userRepository.findByEmail(user.getEmail());
			if(exisitingUser!=null) {
				System.out.println("User is already present");
				throw new UserAlreadyExistException(userdto,"User with this username already exist",false);
			}
			else if(phoneUser!=null)
			{
				throw new UserAlreadyExistException(userdto,"User with this phone already exist",false);
			}
			else if(emailUser!=null)
			{
				throw new UserAlreadyExistException(userdto,"User with this email already exist",false);
			}
			else {
				Set<UserRole> userRoles=new HashSet<>();
				Role role=new Role();
				role.setRoleId(44L);
				role.setRoleName("ROLE_ADMIN");//Every user who sign up will have a NORMAL ROLE
				
				UserRole userRole=new UserRole();
				userRole.setUser(user);
				userRole.setRole(role);
				userRoles.add(userRole );
				for(UserRole ur:userRoles)
				{
					roleRepository.save(ur.getRole());
				}
				user.getUserRoles().addAll(userRoles);
				//encoded the password
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				exisitingUser=this.userRepository.save(user);
		}

			return modelMapper.map(exisitingUser, UserDTO.class);

	    }

		@Override
		public UserDTO getUserByEmail(String email) {
			User user=userRepository.findByEmail(email);
			
			return modelMapper.map(user, UserDTO.class);
		}

		@Override
		public List<UserDTO> findUserInSubject(long cid) {
			List<User> users=userRepository.findBySubjectsEnrolled(cid);
			List<UserDTO> userDTOs= users.stream().map((user)-> this.modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
			return userDTOs;
		}

		@Override
		public CategoryDTO enrollInCategory(long cid,long userid) {
			Optional<Category> optional=categoryRepository.findById(cid);
			Category category=optional.get();
			User user=userRepository.findById(userid).get();
		List<Category>categories=user.getSubjectsEnrolled();
		if(categories.contains(category)==false)
		{
			user.getSubjectsEnrolled().add(category);
		}
			
			userRepository.save(user);
			return modelMapper.map(category,CategoryDTO.class);
		}
}
