package com.example.BlogApp.security;


;
import com.example.BlogApp.blog.entities.User;
import com.example.BlogApp.payloads.CreateUserDto;
import com.example.BlogApp.payloads.JwtAuthResponse;
import com.example.BlogApp.payloads.UserDto;
import com.example.BlogApp.repositories.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthenticationService {
    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepo repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public UserDto register(CreateUserDto request){
        User user = new User();
        user.setName(request.getUsername());
        user.setEmail(request.getEmail());
        user.setAbout(request.getAbout());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user =repository.save(user);

        String token = jwtService.generateToken(user);
        //return new JwtAuthResponse(token);
        return  new UserDto(user.getName(), user.getEmail(), user.getAbout(),token,user.getRole());
    }

    public UserDto authenticationResponse(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername() ,
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getUsername()).orElseThrow();
        String token =jwtService.generateToken(user);
        System.out.println("Bearer "+  token);
       // UserDto userDto = new UserDto(user.getName(), user.getEmail(), user.getAbout(),token);
        return  new UserDto(user.getName(), user.getEmail(), user.getAbout(),token,user.getId(),user.getRole());
    }

}