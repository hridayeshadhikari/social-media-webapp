package com.socialvista.repository;

import com.socialvista.model.Chat;
import com.socialvista.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    public List<Chat> findByUserId(Integer UserId);

    @Query("select c from Chat c where :user Member c.users And :reqUser Member c.users")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
}
