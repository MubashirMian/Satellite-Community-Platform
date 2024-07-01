package com.example.BlogApp.service;

import com.example.BlogApp.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto , Integer postId, Integer userId);
    void deleteComment(Integer commentId);
}
