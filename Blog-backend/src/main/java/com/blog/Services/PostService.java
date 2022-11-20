package com.blog.Services;

import java.util.List;

import com.blog.Entities.Post;
import com.blog.Payloads.PostDto;
import com.blog.Payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer catId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getAllPostsByCategory(Integer catId);
	
	List<PostDto> getAllPostsByUser(Integer userId);
	
	PostResponse getAllPosts(Integer pageNo,Integer pageSize,String sortBy);
	
	List<PostDto> searchPosts(String keyword);
	
	void deletePost( Integer postId );
	
}
