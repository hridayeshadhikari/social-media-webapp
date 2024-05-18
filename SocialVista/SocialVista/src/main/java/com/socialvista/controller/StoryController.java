package com.socialvista.controller;

import com.socialvista.Exceptions.StoryException;
import com.socialvista.Exceptions.UserException;
import com.socialvista.model.Story;
import com.socialvista.model.User;
import com.socialvista.service.StoryService;
import com.socialvista.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StoryController {

    private final StoryService storyService;
    private final UserService userService;
    @PostMapping("/api/story")
    public ResponseEntity<Story> createNewStory(@RequestBody Story story, @RequestHeader ("Authorization") String jwt) throws UserException {
           User user=userService.findUserByToken(jwt);
           Story createdStory=storyService.createStory(story, user.getId());
           return new ResponseEntity<Story>(createdStory,HttpStatus.OK);
    }

    @GetMapping("/api/stories/{userId}")
    public ResponseEntity<List<Story>> getAllStoryOfUser(@PathVariable Integer userId) throws Exception {
        List<Story> allStory=storyService.findStoryByUserId(userId);
        return new ResponseEntity<List<Story>>(allStory,HttpStatus.OK);
    }

    @GetMapping("/api/all/stories")
    public ResponseEntity<List<Story>> getAllUsersStory(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByToken(jwt);
        List<Story> storiesList=storyService.getAllUsersStory(user.getId());
        return new ResponseEntity<List<Story>>(storiesList,HttpStatus.OK);
    }

}
