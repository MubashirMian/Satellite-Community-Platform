package com.example.BlogApp.repositories;

import com.example.BlogApp.blog.entities.Comment;
import com.example.BlogApp.blog.entities.Post;
import com.example.BlogApp.payloads.CommentDto;
import jakarta.persistence.SecondaryTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CommentRepo  extends JpaRepository<Comment,Integer> {
   public Set<Comment> findAllByPost(Post post);

}
