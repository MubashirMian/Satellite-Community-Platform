package com.example.BlogApp.payloads;

import java.util.List;

public class PostResponse {
    private List<PostDto> content;
    private int pageNumber;
    private Long totalElements;
    private int pageSize;
    private int totalPages;
    private boolean lastPage;

    public PostResponse() {
    }

    public List<PostDto> getContent() {
        return content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setContent(List<PostDto> content) {
        this.content = content;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
