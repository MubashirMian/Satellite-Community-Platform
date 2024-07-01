package com.example.BlogApp.payloads;

public class CommentDto {
    private int id;
    private String content;
    private Integer userId;

    public CommentDto() {
    }

    public int getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
