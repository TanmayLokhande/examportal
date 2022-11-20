package com.blog.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Exceptions.*;
import com.blog.Entities.User;
import com.blog.Payloads.UserDto;
import com.blog.Repositories.UserRepo;
import com.blog.Services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		
		User  savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);	
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
	
		User user = this.userRepo.findById(userId).orElseThrow(() -> new  ResourceNotFoundException("User","id",userId)); 
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		
		UserDto userdto1 = this.userToDto(updatedUser);
		
		return userdto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		return  this.userToDto(user);
		
		  
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users =  this.userRepo.findAll();
		List<UserDto> userdtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		
		this.userRepo.delete(user);
	}

	public User dtoToUser( UserDto userdto ) {
		User user  = this.modelMapper.map( userdto,User.class );

		return user;
	}
	
	public UserDto userToDto( User user ) {
		UserDto userdto = this.modelMapper.map(user, UserDto.class);
	
		return userdto;
	}
	
}
