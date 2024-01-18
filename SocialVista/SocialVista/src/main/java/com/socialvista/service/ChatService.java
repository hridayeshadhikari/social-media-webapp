package com.socialvista.service;

import com.socialvista.model.Chat;
import com.socialvista.model.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser , User user2) throws Exception;

    public Chat findChatByID(Integer chatId) throws Exception;

    public List<Chat> findAllChatOfUser(Integer userId) throws Exception;

}
