package com.domain.login_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.login_api.models.User;
import com.domain.login_api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalStateException("User already exist: " + username);
        }

        User newUser = new User(username, password);

        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        // if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
        //     return userOptional;
        // }

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            return userOptional;
        }

        return Optional.empty();
    }
}
