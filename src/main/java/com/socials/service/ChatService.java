package com.socials.service;

import java.util.List;

import com.socials.exceptions.ChatException;
import com.socials.models.Chat;
import com.socials.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws ChatException;
	
	public List<Chat> findUsersChat(Integer userId);

}
