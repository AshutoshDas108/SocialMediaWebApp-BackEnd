package com.socials.controller;

import com.socials.models.Message;
import com.socials.models.User;
import com.socials.service.MessageService;
import com.socials.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private Userservice userservice;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader String jwt,
                                 @PathVariable Integer chatId) throws Exception {

        User user=userservice.findUserByJwt(jwt);

        Message message = messageService.createMessage(user, chatId, req);
        return message;
    }


    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader String jwt,
                                      @PathVariable Integer chatId) throws Exception {

        User user=userservice.findUserByJwt(jwt);

        List<Message> messages = messageService.findChatsMessages(chatId);
        return messages;
    }

}
