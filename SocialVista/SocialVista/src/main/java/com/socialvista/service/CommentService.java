package com.socialvista.service;

import com.socialvista.Exceptions.UserException;
import com.socialvista.model.Comment;

public interface CommentService {

    public Comment createComment(Comment comment,Integer userId,Integer postId) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;

    public Comment likeComment(Integer commentId,Integer userId) throws Exception;

    Comment createReelComment(Comment comment,Integer userId,Integer reelId) throws Exception;
}
