package com.socials.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.socials.exceptions.ChatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socials.models.Chat;
import com.socials.models.User;
import com.socials.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {
	
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat createChat(User reqUser, User user2) {
		
		/*
		if chat already exist then return that 
		        all messaging will be continued in that
				if not then return new chat
		*/
		
		Chat isExist = chatRepository.findChatByUsersId(user2, reqUser) ;
		
		if(isExist != null) {
			return isExist;
		}
		
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setCreatedAt(LocalDateTime.now());
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws ChatException {
		Optional<Chat> opt = chatRepository.findById(chatId);
		
		if(opt.isEmpty()) {
			throw new ChatException("Chat not Found with Id : " + chatId);
		}
		
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		
		return chatRepository.findByUsersId(userId);
		
	}

}
