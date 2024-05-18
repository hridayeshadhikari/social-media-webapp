package com.socialvista.service;

import com.socialvista.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post,Integer userId) throws Exception;
    void deletePost(Integer postId,Integer userId) throws Exception;
    Post findPostById(Integer postId) throws Exception;
    List<Post> findAllPost();
    List<Post> findPostByUserId(Integer userId) throws Exception;
    List<Post> savePost(Integer userId, Integer postId) throws Exception;
    Post likePost(Integer postId,Integer userId) throws Exception;
}
