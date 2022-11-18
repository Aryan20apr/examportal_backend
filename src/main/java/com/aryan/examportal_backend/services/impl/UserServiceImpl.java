package com.aryan.examportal_backend.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.aryan.examportal_backend.Constants.PasswordChangeStatus;
import com.aryan.examportal_backend.exceptions.UserAlreadyExistException;
import com.aryan.examportal_backend.model.Role;
import com.aryan.examportal_backend.model.User;
import com.aryan.examportal_backend.model.UserRole;
import com.aryan.examportal_backend.payload.PasswordChangeDTO;
import com.aryan.examportal_backend.payload.UserDTO;
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
	ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO createUser (UserDTO userdto)throws Exception {
		
		User user=	modelMapper.map(userdto, User.class);
		User exisitingUser=this.userRepository.findByUsername(user.getUsername());
		if(exisitingUser!=null) {
			System.out.println("User is already present");
			throw new UserAlreadyExistException(user.getUsername(),"User with this username already exist");
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
	public UserDTO getUserByUserName(String username) {
		User user=userRepository.findByUsername(username);
		return modelMapper.map(user, UserDTO.class);
		
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	public UserDTO updateUser(UserDTO user, String username) {
		
			
			User existingUser=userRepository.findByUsername(username);
			existingUser.setFirstName(user.getFirstname());
			existingUser.setLastName(user.getLastname());
			existingUser.setEmail(user.getEmail());
			existingUser.setPhone(user.getPhone());
			existingUser.setProfileImage(user.getProfileImage());
			userRepository.save(existingUser);
			
			
			return modelMapper.map(existingUser,UserDTO.class);
		
	}
	
	 @Override
	    public PasswordChangeStatus changePassword(PasswordChangeDTO passwordChangeDTO) {
	        
	        Optional<User> optional= userRepository.findByEmail(passwordChangeDTO.getEmail());
	        
	        User user=optional.get();
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
	        
	        Optional<User> optional= userRepository.findByEmail(passwordChangeDTO.getEmail());
	        
	        User user=optional.get();
	        System.out.println(user.getEmail());
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
			User exisitingUser=this.userRepository.findByUsername(user.getUsername());
			
			System.out.println("User is "+user.getFirstName()+" "+user.getLastName());
			if(exisitingUser!=null) {
				System.out.println("User is already present");
				throw new UserAlreadyExistException(user.getUsername(),"User with this username already exist");
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
}
