package com.socials.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socials.models.Comment;
import com.socials.models.Post;
import com.socials.models.User;
import com.socials.repository.PostRepository;
import com.socials.repository.commentRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostService postService;
	
	
	@Autowired
	private Userservice userservice;
	
	
	@Autowired
	private commentRepository commentRepository;
	
	
	@Autowired
	private PostRepository postRepository;
	
	

	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		
		User user = userservice.findUserById(userId);
		
		Post post = postService.findPostById(postId);
		
		Comment newComment = new Comment();
		
		newComment.setUser(user);
		newComment.setContent(comment.getContent());
		newComment.setCreatedAt(LocalDateTime.now());
		
		Comment savedComment = commentRepository.save(newComment);
		
		post.getComments().add(savedComment);
		
		postRepository.save(post);
		
		return savedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if(opt.isEmpty()) {
			throw new Exception("Comment not found");
		}
	
		return opt.get();
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		
		Comment comment = findCommentById(commentId);
		
		User user = userservice.findUserById(userId);
		
		if(comment.getLiked().contains(user)) {
			comment.getLiked().remove(user);
		}
		else {
			comment.getLiked().add(user);
		}
		
		return commentRepository.save(comment);
	}

}
