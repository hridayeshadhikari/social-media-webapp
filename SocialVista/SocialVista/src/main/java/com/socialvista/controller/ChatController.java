package com.socialvista.controller;

import com.socialvista.model.Chat;
import com.socialvista.model.User;
import com.socialvista.request.CreateChatRequest;
import com.socialvista.service.ChatService;
import com.socialvista.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;
    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader ("Authorization") String jwt,@RequestBody CreateChatRequest req) throws Exception {
        User reqUser=userService.findUserByToken(jwt);
        User user2=userService.findUserById(req.getUserId());
        Chat chat=chatService.createChat(user2,reqUser);
        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> allChatsOfUser(@RequestHeader ("Authorization") String jwt) throws Exception {
        User user=userService.findUserByToken(jwt);
        List<Chat> chats=chatService.findAllChatOfUser(user.getId());

        return chats;
    }


}
