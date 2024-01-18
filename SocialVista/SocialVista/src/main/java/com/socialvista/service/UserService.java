package com.socialvista.service;

import com.socialvista.model.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public List<User> getAllUser();
    public User findUserById(Integer id) throws Exception;
    public User findUserByEmail(String email);
    public User followUser(Integer userId1, Integer userId2) throws Exception;
    public User updateUser(User user,Integer id) throws Exception;
    public List<User> searchUser(String query);
    public User findUserByToken(String jwt);
}
