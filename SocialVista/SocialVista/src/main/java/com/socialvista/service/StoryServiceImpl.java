package com.socialvista.service;

import com.socialvista.Exceptions.StoryException;
import com.socialvista.Exceptions.UserException;
import com.socialvista.model.Story;
import com.socialvista.model.User;
import com.socialvista.repository.StoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StoryServiceImpl implements StoryService{

    private StoryRepository storyRepository;
    private UserService userService;
    @Override
    public Story createStory(Story story, Integer userId) throws UserException {

        User user=userService.findUserById(userId);
        Story addStory=new Story();
        addStory.setUser(user);
        addStory.setVideo(story.getVideo());
        addStory.setCaption(story.getCaption());
        addStory.setImage(story.getImage());
        addStory.setTimestamp(LocalDateTime.now());

        Story createdStory=storyRepository.save(addStory);
        return createdStory;
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        List<Story> allStory=storyRepository.findAllStoryByUserId(userId);
        if(allStory.size()==0){
            throw new Exception("No story is present in this userid");
        }
        return allStory;
    }

}
