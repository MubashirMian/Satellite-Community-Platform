package com.example.BlogApp.payloads;

import com.example.BlogApp.blog.entities.Category;
import com.example.BlogApp.blog.entities.Comment;
import com.example.BlogApp.blog.entities.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

    private CategoryDto category;
    private UserDto user;

   // public Set<Comment> getComments() {
       // return comments;
  // }


   // public void setComments(Set<Comment> comments) {
     //   this.comments = comments;
   // }


    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    public PostDto(Integer postId, String title, String content, String imageName, Date addedDate, CategoryDto category, UserDto user, Set<CommentDto> comments) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.imageName = imageName;
        this.addedDate = addedDate;
        this.category = category;
        this.user = user;
        this.comments = comments;
    }

    private Set<CommentDto> comments =new HashSet<>();

    public PostDto() {
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageName() {
        return imageName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public UserDto getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
