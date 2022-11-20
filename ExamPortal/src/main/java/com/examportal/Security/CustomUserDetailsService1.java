package com.examportal.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examportal.Exceptions.ResourceNotFoundException;
import com.examportal.Repositories.UserRepo;
import com.examportal.entities.User;

@Service
public class CustomUserDetailsService1 implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepo.findByusername(username);
		if(user == null) {
			throw new ResourceNotFoundException("User", "username", username);
		}
		return user;
	}

}
