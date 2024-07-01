package com.example.BlogApp.payloads;


import com.example.BlogApp.blog.entities.Role;

public class CreateUserDto {
    private String username;
    private String password;
    private String email;
    private Role role;
    private String about;

    public CreateUserDto(String username, String password, String email, Role role, String about) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.about = about;
    }

    public CreateUserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
