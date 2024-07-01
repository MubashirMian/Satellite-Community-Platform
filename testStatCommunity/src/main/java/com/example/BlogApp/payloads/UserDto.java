package com.example.BlogApp.payloads;

import com.example.BlogApp.blog.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


public class UserDto {
    private int    id;
    private Role role;
    @NotEmpty
    @Size(min=4, message="Username must be min of 4 character !!")
    private String name;
    @Email
    private String email;
    @NotEmpty
    @Size(min= 3, max =10,message="Password must be minimum of 3 chars and mx of 10 characters")
    private String password;
    @NotEmpty
    private String about;
    private String token;
    public UserDto() {
    }

    public UserDto(String name, String email, String about, String token, int id, Role role) {
        this.name =name;
        this.email= email;
        this.about=about;
        this.token=token;
        this.id=id;
        this.role=role;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public UserDto(String name, String email, String about, String token) {
        this.name = name;
        this.email = email;
        this.about = about;
        this.token=token;

    }

    public int getId() {
        return id;
    }

    public UserDto(String name, String email, String about, String token,Role role) {
        this.name = name;
        this.email = email;
        this.about = about;
        this.token = token;
        this.role = role;

    }
    //ser.getName(), user.getEmail(), user.getAbout(),token,user.getId()
    public UserDto(String  name, String email, String about, String  token, int id){
        this.name=name;
        this.email=email;
        this.about=about;
        this.token=token;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getAbout() {
        return about;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
