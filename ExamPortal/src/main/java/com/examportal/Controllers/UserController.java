package com.examportal.Controllers;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.Services.UserService;
import com.examportal.entities.Role;
import com.examportal.entities.User;
import com.examportal.entities.User_Role;
import com.examportal.payLoads.ApiResponse;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/")
	public User createUser( @RequestBody User user ) throws Exception {
		
		Set<User_Role> roles = new HashSet<>();
		user.setProfile("defalut.png");
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		Role role = new Role();
		role.setRoleName("NORMAL");
		
		User_Role userRole = new User_Role();
		userRole.setUser(user);
		userRole.setRole(role);
		
		roles.add(userRole);
		return this.userService.createUser(user, roles);
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	
	@DeleteMapping("/{username}")
	public ApiResponse deleteUser(@PathVariable("username") String username) {
		this.userService.deleteUser(username);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("User deleted successfully!!");
		apiResponse.setStatus(true);
		return apiResponse;
	}
	
	@PutMapping("/{username}")
	public User updateUser(@RequestBody User user,@PathVariable("username") String username) {
		return this.userService.updateUser(user, username);
	}

}