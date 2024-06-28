package com.domain.login_api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.login_api.dto.RequestUserDTO;
import com.domain.login_api.dto.ResponseDTO;
import com.domain.login_api.dto.TokenDTO;
import com.domain.login_api.models.User;
import com.domain.login_api.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<User>> registerUser(@RequestBody RequestUserDTO requestUserDTO) {
        ResponseDTO<User> response = new ResponseDTO<>();

        try {
            User registeredUser = userService.registerUser(requestUserDTO);
            response.setStatus(true);
            response.setData(registeredUser);
            response.getMessages().add("User registered successfully");
            return ResponseEntity.status(200).body(response);
        } catch (IllegalStateException e) {
            response.setStatus(false);
            response.getMessages().add("Username already registered");
            return ResponseEntity.status(400).body(response);
        }        
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<TokenDTO>> loginUser(@RequestBody RequestUserDTO requestUserDTO) {
        ResponseDTO<TokenDTO> response = new ResponseDTO<>();
        Optional<String> token = userService.loginUser(requestUserDTO); 

        if (token.isPresent()) {
            response.setStatus(true);
            response.setData(new TokenDTO(token.get()));
            response.getMessages().add("User successfully login");
            return ResponseEntity.status(200).body(response);
        } else {
            response.setStatus(false);
            response.getMessages().add("Invalid username or password");
            return ResponseEntity.status(401).body(response);
        }
    }
}
  