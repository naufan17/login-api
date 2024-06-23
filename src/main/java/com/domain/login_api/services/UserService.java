package com.domain.login_api.services;

import java.util.Optional;

import com.domain.login_api.models.User;
import com.domain.login_api.models.dto.UserTokenDTO;

public interface UserService {
    User registerUser(String username, String password);

    Optional<UserTokenDTO> loginUser(String username, String password);
}