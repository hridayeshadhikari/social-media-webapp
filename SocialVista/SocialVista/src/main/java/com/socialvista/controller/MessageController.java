package com.socialvista.controller;


import com.socialvista.model.Message;
import com.socialvista.model.User;
import com.socialvista.service.MessageService;
import com.socialvista.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader ("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {
        User requser=userService.findUserByToken(jwt);
        Message message =messageService.createMessage(requser,chatId,req);
        return message;
    }

    @GetMapping ("/api/messages/chat/{chatId}")
    public List<Message> messageByChatID(@RequestHeader ("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {
        User requser=userService.findUserByToken(jwt);
        List<Message> messages =messageService.findMessageByChatId(chatId);
        return messages;
    }

}
