package com.example.BlogApp.service.impl;

import com.example.BlogApp.blog.entities.Category;
import com.example.BlogApp.blog.entities.Comment;
import com.example.BlogApp.blog.entities.Post;
import com.example.BlogApp.blog.entities.User;
import com.example.BlogApp.exceptions.ResourceNotFoundException;
import com.example.BlogApp.payloads.CommentDto;
import com.example.BlogApp.payloads.PostDto;
import com.example.BlogApp.payloads.PostResponse;
import com.example.BlogApp.repositories.CategoryRepo;
import com.example.BlogApp.repositories.CommentRepo;
import com.example.BlogApp.repositories.PostRepo;
import com.example.BlogApp.repositories.UserRepo;
import com.example.BlogApp.service.PostService;
import org.hibernate.annotations.Comments;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private CommentRepo commentRepo;



    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
        Category category= this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category", "category id",categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(()-> new ResourceNotFoundException("Post","post id ",postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }
        // Pagination and sorting
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Fetch posts from repository
        Page<Post> pagePost = this.postRepo.findAll(pageable);

        // Convert Post entities to PostDto
        List<PostDto> postDtos = pagePost.getContent().stream()
                .map(post -> {
                    PostDto postDto = this.modelMapper.map(post, PostDto.class);
                    postDto.setPostId(post.getPostId()); // Set postId

                    // Fetch comments for the post and map to CommentDto
                    Set<Comment> comments = this.commentRepo.findAllByPost(post);
                    Set<CommentDto> commentDtos = comments.stream()
                            .map(comment -> this.modelMapper.map(comment, CommentDto.class))
                            .collect(Collectors.toSet());
                    postDto.setComments(commentDtos);

                    return postDto;
                })
                .collect(Collectors.toList());

        // Create PostResponse
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }



    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        // Map the post entity to the PostDto
        PostDto postDto = this.modelMapper.map(post, PostDto.class);

        // Find all comments for the post
        Set<Comment> comments = this.commentRepo.findAllByPost(post);

        // Map the comments to CommentDto
        Set<CommentDto> dto = comments.stream()
                .map(comment -> this.modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toSet());

        // Set the comments in the PostDto
        postDto.setComments(dto);

        return postDto;
    }
    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
        List<Post> allPosts = this.postRepo.findByUser(user);
        Set<CommentDto> dto =null;
        List<PostDto> postDtos =allPosts.stream()
                .map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        for(PostDto x : postDtos){
            Post post =this.modelMapper.map(x,Post.class);
        Set<Comment> comments = this.commentRepo.findAllByPost(post);
        dto =  comments.stream().map((comment) -> this.modelMapper.map(comment,CommentDto.class))
                .collect(Collectors.toSet());
        x.setComments(dto);

        }

        return postDtos;

    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
          Category cat=  this.categoryRepo.findById(categoryId)
                  .orElseThrow(()-> new ResourceNotFoundException("Category","categpory id",categoryId));
          List<Post> posts = this.postRepo.findByCategory(cat);

          List<PostDto> postDtos =posts.stream()
                  .map((post) -> this.modelMapper.map(post, PostDto.class))
                  .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String Keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(Keyword);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }
}
