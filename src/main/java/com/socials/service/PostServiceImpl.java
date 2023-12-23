package com.socials.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.socials.exceptions.PostException;
import com.socials.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socials.models.Post;
import com.socials.models.User;
import com.socials.repository.PostRepository;
import com.socials.repository.UserRepository;


@Service
public class PostServiceImpl implements PostService{
	
	
	@Autowired
    PostRepository postRepository;
	
	@Autowired
	Userservice userservice ;
	
	@Autowired
	UserRepository userRepository;

	
	
	
	@Override
	public Post createNewPost(Post post, Integer userId) throws UserException {
		Post newPost = new Post();
		User user = userservice.findUserById(userId);
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		return postRepository.save(newPost);
	}

	
	
	
	@Override
	public String deletePost(Integer postId, Integer userId) throws UserException, PostException {
	   Post post = findPostById(postId);
	   User user = userservice.findUserById(userId);
	   
	   if(post.getUser().getId() != user.getId()) {
		   throw new PostException("Can't Delete anathor user's post");
	   }
	   
		postRepository.deleteById(postId);
		return "Post Deleted Successfully";
	}

	
	
	
	
	@Override
	public List<Post> findPostByUserId(Integer userId) throws PostException {
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new PostException("User Not Found");
		}
		
		return postRepository.findPostByUserId(userId);
		
	}

	
	
	
	
	@Override
	public Post findPostById(Integer postId) throws PostException {
		Optional<Post> optPost = postRepository.findById(postId);
		if(optPost.isEmpty()) {
			throw new PostException("Post Not Found");
		}
		return optPost.get();
	}

	
	
	
	
	
	@Override
	public List<Post> findAllPost() {
		List<Post> posts = postRepository.findAll();
		return posts;
	}

	
	
	
	
	
	
	@Override
	public Post savePost(Integer postId, Integer userId) throws PostException, UserException {
		Post post = findPostById(postId);
		User user = userservice.findUserById(userId);
		
		if(user.getSavedPosts().contains(post)) {
			user.getSavedPosts().remove(post);
		}
		else {
			user.getSavedPosts().add(post);
		}
		
		userRepository.save(user);
		return post;
		
	}

	
	
	
	
	
	@Override
	public Post likePost(Integer postId, Integer userId) throws PostException, UserException {
		Post post = findPostById(postId);
		User user = userservice.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
		post.getLiked().add(user);
		}
		
		return postRepository.save(post);
	}
	
	
	
}
