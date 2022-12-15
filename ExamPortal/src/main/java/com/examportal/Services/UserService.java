package com.examportal.Services;

import java.util.Set;

import com.examportal.entities.User;
import com.examportal.entities.User_Role;

public interface UserService {

	public User createUser(User user,Set<User_Role> userRoles) throws Exception;
	
	public User getUser(String username);
	
	public void deleteUser(String username);
	
	public User updateUser(User user,String  username);
	
	public User getUserById(Integer userId);
	
}
