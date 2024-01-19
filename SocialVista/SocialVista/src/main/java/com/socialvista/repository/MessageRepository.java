package com.socialvista.repository;


import com.socialvista.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    //finds all the message with the chat id
    public List<Message> findByChatId(Integer chatId);
}
