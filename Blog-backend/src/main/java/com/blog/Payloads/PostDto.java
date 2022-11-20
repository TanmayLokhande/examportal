package com.blog.Payloads;

import java.util.Date;

import javax.persistence.ManyToOne;

import com.blog.Entities.Category;
import com.blog.Entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	private String postTitle;
	private String postContent;
	
	private String imageName;
	private Date addedDate;
	

	private CategoryDto category;
	
	
	private UserDto user;
	
	
}
