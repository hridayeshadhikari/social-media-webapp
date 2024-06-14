package com.socialvista.service;

import com.socialvista.Exceptions.UserException;
import com.socialvista.model.Comment;
import com.socialvista.model.Post;
import com.socialvista.model.Reel;
import com.socialvista.model.User;
import com.socialvista.repository.CommentRepository;
import com.socialvista.repository.PostRepository;
import com.socialvista.repository.ReelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{


    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ReelService reelService;
    private final ReelRepository reelRepository;
    @Override
    public Comment createComment(Comment comment, Integer userId, Integer postId) throws Exception {
        User user=userService.findUserById(userId);
        Post post=postService.findPostById(postId);

        comment.setUser(user);
        comment.setDescription(comment.getDescription());
        comment.setCreatedAt(LocalDateTime.now());
        Comment savecomment=commentRepository.save(comment);
        post.getComments().add(savecomment);
        postRepository.save(post);
        return savecomment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt=commentRepository.findById(commentId);
        if (opt.isEmpty()){
            throw new Exception("no comment found with this id");
        }

        return opt.get();
    }

    @Override
    public Comment likeComment( Integer commentId,Integer userId) throws Exception {
        Comment comment=findCommentById(commentId);
        User user=userService.findUserById(userId);
        if(comment.getLiked().contains(user)){
            comment.getLiked().remove(user);
        }
        else {
            comment.getLiked().add(user);
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment createReelComment(Comment comment, Integer userId, Integer reelId) throws Exception {
        User user=userService.findUserById(userId);
        Reel reel=reelService.findById(reelId);
        comment.setUser(user);
        comment.setDescription(comment.getDescription());
        comment.setCreatedAt(LocalDateTime.now());

        Comment saveComment=commentRepository.save(comment);
        reel.getComment().add(saveComment);
        reelRepository.save(reel);
        return saveComment;
    }
}
