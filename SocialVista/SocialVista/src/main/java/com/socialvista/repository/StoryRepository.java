package com.socialvista.repository;

import com.socialvista.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface StoryRepository extends JpaRepository<Story,Integer> {

    @Query("select s from Story s where s.user.id= :userId")
    List<Story> findAllStoryByUserId(@Param("userId") Integer userID);

    @Query("SELECT s FROM Story s WHERE s.id IN (SELECT MIN(s1.id) FROM Story s1 WHERE s1.user.id = :userId GROUP BY s1.user.id)")
    List<Story> findFirstStoryByUserId(@Param("userId") Integer userId);



}
