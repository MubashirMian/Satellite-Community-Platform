package com.example.BlogApp.repositories;

import com.example.BlogApp.blog.entities.Category;
import com.example.BlogApp.blog.entities.Post;
import com.example.BlogApp.blog.entities.User;
import com.example.BlogApp.payloads.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
