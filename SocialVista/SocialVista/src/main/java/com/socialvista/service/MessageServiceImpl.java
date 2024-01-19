package com.socialvista.service;

import com.socialvista.model.Chat;
import com.socialvista.model.Message;
import com.socialvista.model.User;
import com.socialvista.repository.ChatRepository;
import com.socialvista.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final ChatRepository chatRepository;
    @Override
    public List<Message> findMessageByChatId(Integer chatId) throws Exception {
        Chat chat=chatService.findChatByID(chatId);
        return messageRepository.findByChatId(chatId);
    }

    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {
        Chat chat=chatService.findChatByID(chatId);
        Message message=new Message();
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());
        Message saveMessage=messageRepository.save(message);
        chat.getMessages().add(saveMessage);
        chatRepository.save(chat);
        return saveMessage;
    }
}