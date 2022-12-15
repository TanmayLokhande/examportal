package com.examportal.Services.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.Exceptions.ResourceNotFoundException;
import com.examportal.Repositories.RoleRepo;
import com.examportal.Repositories.UserRepo;
import com.examportal.Services.UserService;
import com.examportal.entities.User;
import com.examportal.entities.User_Role;
import com.examportal.payLoads.ApiResponse;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public User createUser(User user, Set<User_Role> userRoles) throws Exception {
		
		User local = this.userRepo.findByusername(user.getUsername());
		if(local != null) {
			System.out.println("User already there");
			throw new Exception("User already present");
		}
		else {
			
			for(User_Role ur:userRoles) {
				this.roleRepo.save(ur.getRole());
			}
			user.getUser_roles().addAll(userRoles);
			local = this.userRepo.save(user);
			
			
		}
		return local;
	}

	@Override
	public User getUser(String username) {
		User user = this.userRepo.findByusername(username);
		if(user == null) {
			throw new ResourceNotFoundException("User","username",username);
		}
		return user;
	}

	@Override
	public void deleteUser(String username) {
		User user =	getUser(username);
		this.userRepo.delete(user);
		
	
	}

	@Override
	public User updateUser(User user,String username) {
		User user1 = getUser(username);
		user1.setEmail(user.getEmail());
		user1.setName(user.getName());
		user1.setPassword(user.getPassword());
		user1.setUsername(user.getUsername());
		
		User updatedUser = this.userRepo.save(user1);
		return updatedUser;
	}

	@Override
	public User getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId"+userId, ""));
		return user;
	}

}
