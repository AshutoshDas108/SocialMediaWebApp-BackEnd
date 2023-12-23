package com.socials.controller;

import java.util.List;

import com.socials.exceptions.ChatException;
import com.socials.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socials.models.Chat;
import com.socials.request.ChatRequest;
import com.socials.service.ChatService;
import com.socials.service.Userservice;
import com.socials.models.User;

@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private Userservice userservice;
	
	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt, 
			                 @RequestBody ChatRequest req) throws  UserException {
		
		User reqUser = userservice.findUserByJwt(jwt);
		User user2 = userservice.findUserById(req.getUserId());
		Chat chat = chatService.createChat(reqUser, user2);
		
		return chat;
	}
	
	
	@GetMapping("/api/chats")
	public List<Chat> findUserChat(@RequestHeader("Authorization") String jwt) throws UserException {
		
		User user = userservice.findUserByJwt(jwt);
		
		List<Chat> chats = chatService.findUsersChat(user.getId());
		
		return chats;
	}


}
