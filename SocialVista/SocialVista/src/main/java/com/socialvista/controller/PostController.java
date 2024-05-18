package com.socialvista.controller;

import com.socialvista.model.Post;
import com.socialvista.model.User;
import com.socialvista.repository.UserRepository;
import com.socialvista.service.PostService;
import com.socialvista.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;

    //gets all the post that has been posted
    @GetMapping("/api/user/posts")
    public ResponseEntity<List<Post>> getAllPost(){
       List<Post> posts=postService.findAllPost();
       return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    //creates a new post
    @PostMapping("/api/post")
    public ResponseEntity<Post> createPosts(@RequestBody Post post ,
                                            @RequestHeader ("Authorization")String jwt) throws Exception {
        User reqUser=userService.findUserByToken(jwt);
        Post savePost=postService.createPost(post,reqUser.getId());
        return new ResponseEntity<>(savePost, HttpStatus.ACCEPTED);
    }

    //delete the post by particular user itself which he has posted
    @DeleteMapping("/api/delete/post/{postId}")
    public ResponseEntity<String> deleteThePost(@PathVariable Integer postId,
                                                @RequestHeader ("Authorization")String jwt
                                                ) throws Exception {
        User reqUser=userService.findUserByToken(jwt);
        postService.deletePost(postId,reqUser.getId());
        return ResponseEntity.ok("deleted successfully");
    }

    //find the post based on its id
    @GetMapping("/api/post/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {
        Post post=postService.findPostById(postId);
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }
    //find the posts of particular user based on his id

    @GetMapping("/api/user/post/{userId}")
    public ResponseEntity<List<Post>> postByUserId(@PathVariable Integer userId) throws Exception {
        List<Post> posts=postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    //saves the post/updates
    @PutMapping("/api/posts/save/{postId}")
    public ResponseEntity<List<Post>> savePost(@PathVariable Integer postId,
                                         @RequestHeader ("Authorization")String jwt) throws Exception {
        User reqUser=userService.findUserByToken(jwt);
        List<Post> post=postService.savePost(postId, reqUser.getId());
        return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
    }
    @GetMapping("/api/posts/save")
    public ResponseEntity<List<Post>> getSavePost(@RequestHeader ("Authorization")String jwt){
        User user=userService.findUserByToken(jwt);
        List<Post> getSavedPost=user.getSavePost();
        return ResponseEntity.ok(getSavedPost);
    }

    //like a particular post
    @PutMapping("/api/like/post/{postId}")
    public  ResponseEntity<Post> likePost(@PathVariable Integer postId,
                                          @RequestHeader ("Authorization")String jwt) throws Exception {
        User reqUser=userService.findUserByToken(jwt);
        Post post=postService.likePost(postId,reqUser.getId());
        return new ResponseEntity<Post>(post,HttpStatus.OK);
    }




}
