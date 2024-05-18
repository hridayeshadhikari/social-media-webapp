package com.socialvista.service;

import com.socialvista.Exceptions.StoryException;
import com.socialvista.Exceptions.UserException;
import com.socialvista.model.Story;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story ,Integer userId) throws UserException;
    public List<Story> findStoryByUserId (Integer userId) throws Exception;

    public List<Story> getAllUsersStory(Integer reqUserId) throws Exception;
}
