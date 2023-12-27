package com.socials.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socials.models.Post;
import com.socials.models.User;
import com.socials.response.ApiResponse;
import com.socials.service.PostService;
import com.socials.service.Userservice;


@RestController
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private Userservice userservice;
	
	
	/*
	Logged-in user can create post
	only from his/her account
	*/
	
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post post, 
			                               @RequestHeader("Authorization")String jwt)
			                            		   throws Exception{
		
		User reqUser = userservice.findUserByJwt(jwt);
		
		Post createdPost = postService.createNewPost(post, reqUser.getId());
		
		return new ResponseEntity<Post>(createdPost,HttpStatus.ACCEPTED);
	}
	
	
	
	/*
	Logged-in user can delete post only
	from his own account
	*/
	
	@DeleteMapping("/api/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,
			                                     @RequestHeader("Authorization")String jwt )
			                                    		 throws Exception{
		
		User reqUser = userservice.findUserByJwt(jwt);
		
		String message = postService.deletePost(postId, reqUser.getId());
		
		ApiResponse res = new ApiResponse(message, true);
		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/api/post/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception{
		Post post = postService.findPostById(postId);
		
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/api/post/user/{userId}")
	public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Integer userId) throws Exception{
		List<Post> posts = postService.findPostByUserId(userId);
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost() {
		List<Post> posts = postService.findAllPost();
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	
	
	
	/*
	Logged-in user can save post only
	to his own account
	*/
	
	@PutMapping("/api/post/save/{postId}")
	public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId, 
			                                    @RequestHeader("Authorization")String jwt) 
			                                    		throws Exception{
		
		User reqUser = userservice.findUserByJwt(jwt);
		
	    Post post = postService.savePost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	
	
	
	
	/*
	Logged-in user can like post only
	from his own account
	*/
	
	@PutMapping("/api/post/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, 
			                                    @RequestHeader("Authorization")String jwt)
			                                    		throws Exception{
		
		User reqUser = userservice.findUserByJwt(jwt);
		
	    Post post = postService.likePost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
}

