package com.domain.login_api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.login_api.controllers.dto.UserDTO;
import com.domain.login_api.models.User;
import com.domain.login_api.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            User registeredUser = userService.registerUser(userDTO.getUsername(),  userDTO.getPassword());
            return ResponseEntity.status(201).body(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Username already exist");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        Optional<User> loggedInUser =  userService.loginUser(userDTO.getUsername(),  userDTO.getPassword()); 
        if (loggedInUser.isPresent()) {
            return ResponseEntity.ok(loggedInUser.get());
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
