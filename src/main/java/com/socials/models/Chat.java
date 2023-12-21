package com.socials.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String chatName;
	
	private String chatImage;
	
	
	/*
	multiple user can
	create mutiple chat
	*/
	
	@ManyToMany
	private List<User> users = new ArrayList<>(); //2 users -- between whom chat taking place
	
	private LocalDateTime createdAt;
	

}
