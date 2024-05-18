package com.socialvista.service;

import com.socialvista.model.Post;
import com.socialvista.model.User;
import com.socialvista.repository.PostRepository;
import com.socialvista.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    @Override
    public Post createPost(Post post, Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        Post addPost=new Post();
        addPost.setCaption(post.getCaption());
        addPost.setImage(post.getImage());
        addPost.setCreatedAt(LocalDateTime.now());
        addPost.setVideo(post.getVideo());
        addPost.setUser(user);
        return postRepository.save(addPost);
    }

    @Override
    public void deletePost(Integer postId, Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        Post post=findPostById(postId);
        if(!post.getUser().getId().equals(user.getId())){
            throw new Exception("can't delete another user's post");

        }
        postRepository.delete(post);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt=postRepository.findById(postId);
        if(opt.isPresent()){
            return opt.get();
        }else {
            throw new Exception("no post found by this id "+postId);
        }

    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll() ;
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) throws Exception {
        return postRepository.findPostByUserId(userId);
    }

    public List<Post> getSavedPosts(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        return user.getSavePost();
    }
    @Override
    public List<Post> savePost(Integer postId, Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        Post post=findPostById(postId);
        if(user.getSavePost().contains(post)){
            user.getSavePost().remove(post);
            userRepository.save(user);
        }
        else{
            user.getSavePost().add(post);
            userRepository.save(user);
        }
        return getSavedPosts(userId);
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        Post post=findPostById(postId);
        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);

        }else{
        post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
