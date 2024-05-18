package com.socialvista.controller;

import com.socialvista.model.Comment;
import com.socialvista.model.User;
import com.socialvista.service.CommentService;
import com.socialvista.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    @PostMapping("/api/post/comment/{postId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader ("Authorization")String jwt,
                                 @PathVariable Integer postId) throws Exception {
        User user=userService.findUserByToken(jwt);
        Comment commentDone=commentService.createComment(comment, user.getId(), postId);
        return commentDone;
    }

    @GetMapping("/api/post/comment/{commentId}")
    public Comment getById(@PathVariable Integer commentId) throws Exception {
        Comment commentFound=commentService.findCommentById(commentId);
        return commentFound;
    }
    @PutMapping("/api/post/comment/like/{commentId}")
    public Comment likeComment(@PathVariable Integer commentId,
                               @RequestHeader ("Authorization")String jwt) throws Exception {
        User user=userService.findUserByToken(jwt);
        Comment likeComment=commentService.likeComment(commentId, user.getId());
        return likeComment;
    }


}
