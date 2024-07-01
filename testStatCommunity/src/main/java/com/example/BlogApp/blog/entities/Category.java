package com.example.BlogApp.blog.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name="title", length=100,nullable = false)
    private String categoryTitle;
    @Column(name="description")
    private String categoryDescription;
    // post contains categories
    // if category is deleted then all associated posts will be deleted
    @OneToMany(mappedBy = "category",cascade=CascadeType.ALL,fetch= FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
    public Category() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
