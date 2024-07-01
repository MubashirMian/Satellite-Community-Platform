package com.example.BlogApp.controllers;

import com.example.BlogApp.payloads.CommentDto;
import com.example.BlogApp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/comments/{userId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable Integer postId,
                                                    @PathVariable Integer userId,
                                                    @RequestBody CommentDto commentDto) {
        // Pass both postId and userId to the createComment method
        CommentDto createdComment = commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
