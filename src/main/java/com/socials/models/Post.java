package com.socials.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Posts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String caption;
	
	private String image;
	
    private String video;
	
    @ManyToOne
	private User user;
	
	private LocalDateTime createdAt;
	
    @ManyToMany
	private List<User> liked = new ArrayList<>();
	
	
	/*
	one post can have multiple 
	comments
	
	one comment generated for a 
	single post
	*/
	
	@OneToMany
	private List<Comment> comments = new ArrayList<>();


}
