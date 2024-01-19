package com.socialvista.service;

import com.socialvista.model.Chat;
import com.socialvista.model.User;
import com.socialvista.repository.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    @Override
    public Chat createChat(User reqUser, User user2) throws Exception {
       Chat isExist=chatRepository.findChatByUsersId(reqUser , user2 );
       if(isExist!=null){
           return isExist;
       }
       Chat chat=new Chat();
       chat.getUsers().add(user2);
       chat.getUsers().add(reqUser);
       chat.setTimestamp(LocalDateTime.now());

       return chatRepository.save(chat);
    }

    @Override
    public Chat findChatByID(Integer chatId) throws Exception {
        Optional<Chat> opt=chatRepository.findById(chatId);
        if(opt.isEmpty()){
            throw new Exception("no chat is found with this id"+chatId);
        }
        return opt.get();
    }

    @Override
    public List<Chat> findAllChatOfUser(Integer userId) throws Exception {
        return chatRepository.findByUsersId(userId);
    }
}
