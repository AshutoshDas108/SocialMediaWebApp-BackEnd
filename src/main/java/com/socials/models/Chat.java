package com.socials.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
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

	/*
	when we give mappedBy="chat" it doesn't create
	a separate table (message_chat)
	 */
	@OneToMany(mappedBy = "chat")
	private List<Message> messages = new ArrayList<>();
	
	private LocalDateTime createdAt;
	

}
