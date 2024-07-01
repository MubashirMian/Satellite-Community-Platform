package com.example.BlogApp.service;

import com.example.BlogApp.blog.entities.Post;
import com.example.BlogApp.payloads.PostDto;
import com.example.BlogApp.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    PostDto getPostById(Integer postId);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> searchPost(String Keyword);


}
