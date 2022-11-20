package com.examportal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByusername(String userName);
	
}
