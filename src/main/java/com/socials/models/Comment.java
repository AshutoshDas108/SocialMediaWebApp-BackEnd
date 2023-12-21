package com.socials.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String content;
	
	
	
	
	
	
	/*
	One comment can be created by a 
	single user.
	
	A single user can create multiple 
	comments 
	*/
	
	@ManyToOne
	private User user; 
	
	
	
	
	
	/*
	Multiple comments can be liked by
	Multiple users no restrictions
	*/
	
	@ManyToMany
	private List<User> liked = new ArrayList<>();
	
	
	private LocalDateTime createdAt;


}
