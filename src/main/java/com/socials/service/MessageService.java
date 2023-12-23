package com.socials.service;

import com.socials.models.Chat;
import com.socials.models.Message;
import com.socials.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message message) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;



}
