package com.socials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socials.models.Comment;
import com.socials.models.User;
import com.socials.service.CommentService;
import com.socials.service.Userservice;

@RestController
public class CommentController {
	
	
	@Autowired
	private CommentService commentService;
	
	
	@Autowired
	private  Userservice userservice;
	
	
	
	@PostMapping("/api/comment/post/{postId}")
	public Comment createComment(@RequestBody Comment comment, 
			    @RequestHeader("Authorization") String jwt,
			    @PathVariable Integer postId) throws Exception {
		
		User user = userservice.findUserByJwt(jwt);
		
		Comment commentFromBody = commentService.createComment(comment, postId, user.getId());
		
		return commentFromBody;
	}
	
	@PutMapping("/api/comment/like/{commentId}")
	public Comment likeComment(
			    @RequestHeader("Authorization") String jwt,
			    @PathVariable Integer commentId) throws Exception {
		
		User user = userservice.findUserByJwt(jwt);
		
		Comment likedComment = commentService.likeComment(commentId, user.getId());
		
		return likedComment;
	}
}
