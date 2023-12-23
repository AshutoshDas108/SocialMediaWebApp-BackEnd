package com.socials.service;

import com.socials.exceptions.CommentException;
import com.socials.exceptions.PostException;
import com.socials.exceptions.UserException;
import com.socials.models.Comment;

public interface CommentService {

	
	public Comment createComment (Comment comment, Integer postId, Integer userId) throws CommentException, UserException, PostException;
	
	public Comment findCommentById(Integer commentId) throws CommentException;
	
	public Comment likeComment (Integer commentId, Integer userId) throws CommentException, UserException;
	
	
}
