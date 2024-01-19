package com.socialvista.service;

import com.socialvista.model.Chat;
import com.socialvista.model.Message;
import com.socialvista.model.User;

import java.util.List;

public interface MessageService {
    public List<Message> findMessageByChatId(Integer chatId) throws Exception;

    public Message createMessage(User user, Integer chatId,Message req) throws Exception;
}
