package com.example.BlogApp.service.impl;

import com.example.BlogApp.blog.entities.Comment;
import com.example.BlogApp.blog.entities.Post;
import com.example.BlogApp.blog.entities.User;
import com.example.BlogApp.exceptions.ResourceNotFoundException;
import com.example.BlogApp.payloads.CommentDto;
import com.example.BlogApp.repositories.CommentRepo;
import com.example.BlogApp.repositories.PostRepo;
import com.example.BlogApp.repositories.UserRepo;
import com.example.BlogApp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;



    // Update the CommentServiceImpl class
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        // Validate the postId and userId
        if (postId == null || userId == null) {
            throw new IllegalArgumentException("Post ID and User ID must not be null");
        }

        // Fetch the post by postId
        Post post = this.postRepo.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "post id", postId));

        // Fetch the user by userId
        User user = this.userRepo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "user id", userId));

        // Map the comment DTO to the Comment entity
        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        // Set the post and user for the comment
        comment.setPost(post);
        comment.setUser(user);

        // Save the comment to the repository
        Comment savedComment = this.commentRepo.save(comment);

        // Map the saved comment entity back to the CommentDto and return
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "comment id", commentId));
        this.commentRepo.delete(comment);
    }
}
