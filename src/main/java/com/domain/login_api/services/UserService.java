package com.domain.login_api.services;

import java.util.Optional;

import com.domain.login_api.dto.RequestUserDTO;
import com.domain.login_api.models.User;

public interface UserService {
    User registerUser(RequestUserDTO requestUserData);

    Optional<String> loginUser(RequestUserDTO requestUserData);
}