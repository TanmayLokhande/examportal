package com.blog.Payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 4,message="Username must be min of 4 characters")
	private String name;
	
	@Email(message = "Your email address is not valid")
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String about;
	
}
