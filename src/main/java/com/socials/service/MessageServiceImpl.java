package com.socials.service;

import com.socials.exceptions.ChatException;
import com.socials.exceptions.MessageException;
import com.socials.models.Chat;
import com.socials.models.Message;
import com.socials.models.User;
import com.socials.repository.ChatRepository;
import com.socials.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;


    @Override
    public Message createMessage(User user, Integer chatId, Message message) throws  ChatException {

        Chat chat = chatService.findChatById(chatId);

        Message createdMessage = new Message();
        createdMessage.setChat(chat);
        createdMessage.setContent(message.getContent());
        createdMessage.setImage(message.getImage());
        createdMessage.setUser(user);
        createdMessage.setTimeStamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(createdMessage);
        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws ChatException {
        //Already handles exception --> if no chat exist throws exception
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}
