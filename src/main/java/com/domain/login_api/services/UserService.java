package com.domain.login_api.services;

import java.util.Optional;

import com.domain.login_api.models.User;

public interface UserService {
    User registerUser(String username, String password);

    Optional<User> loginUser(String username, String password);
}