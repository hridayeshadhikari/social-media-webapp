package com.socialvista.controller;

import com.socialvista.Exceptions.UserException;
import com.socialvista.model.User;
import com.socialvista.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;



    @GetMapping("/api/users")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable Integer id) throws UserException {
        return userService.findUserById(id);

    }
    @GetMapping("/api/user/email")
    public User getByEmail(@PathVariable String email){
        return userService.findUserByEmail(email);
    }

    @PutMapping("/api/user/update")
    public User updateTheUser(@RequestBody User user,@RequestHeader ("Authorization")String jwt) throws UserException {
        User reqUser=userService.findUserByToken(jwt);
        User updateUser=userService.updateUser(user,reqUser.getId());
        return updateUser;
    }

    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader ("Authorization")String jwt,@PathVariable Integer userId2) throws Exception {
        User reqUser=userService.findUserByToken(jwt);
        return userService.followUser(reqUser.getId(),userId2);
    }
    @GetMapping("/api/users/search")
    public List<User> searchUser(@Param("query")String query){
        return userService.searchUser(query);
    }

    @GetMapping("/api/user/profile")
    public User getUserFromToken(@RequestHeader ("Authorization")String jwt){
        User user=userService.findUserByToken(jwt);
        user.setPassword(null);
        return user;
    }

}
