package com.domain.login_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserControler {

    @GetMapping
    public String getUsername() {
        return "Welcome Username";
    }   
}
