package com.example.BlogApp.payloads;

import com.example.BlogApp.blog.entities.Role;

public class JwtAuthResponse {


    public UserDto userDto;
    public Role role;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private String token;

    public JwtAuthResponse(UserDto userDto, Role role, String token) {
        this.userDto = userDto;
        this.role = role;
        this.token = token;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
    public UserDto getUserDto() {
        return userDto;
    }
    public JwtAuthResponse(String token,UserDto userDto) {
        this.token = token;
        this.userDto=userDto;
    }

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
