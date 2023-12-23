package com.socials.service;

import java.util.List;

import com.socials.exceptions.PostException;
import com.socials.exceptions.UserException;
import com.socials.models.Post;

public interface PostService {

	public Post createNewPost(Post post, Integer userId) throws PostException, UserException;
	
	public String deletePost(Integer postId, Integer userId) throws PostException, UserException;
	
	public List<Post> findPostByUserId(Integer userId) throws PostException;
	
	public Post findPostById(Integer postId) throws PostException;
	
	public List<Post> findAllPost();
	
	public Post savePost(Integer PostId, Integer userId) throws PostException, UserException;
	
	public Post likePost(Integer postId, Integer userId) throws PostException, UserException;
}
