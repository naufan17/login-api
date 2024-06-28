package com.domain.login_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.login_api.dto.RequestUserDTO;
import com.domain.login_api.models.User;
import com.domain.login_api.repository.UserRepository;
import com.domain.login_api.utils.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User registerUser(RequestUserDTO requestUserDTO) {
        if (userRepository.existsByUsername(requestUserDTO.getUsername())) {
            throw new IllegalStateException("User already exist: " + requestUserDTO.getUsername());
        }

        User newUser = new User(requestUserDTO.getUsername(), requestUserDTO.getPassword());
        newUser.setPassword(passwordEncoder.encode(requestUserDTO.getPassword()));
        
        return userRepository.save(newUser);
    }

    @Override
    public Optional<String> loginUser(RequestUserDTO requestUserDTO) {
        Optional<User> userOptional = userRepository.findByUsername(requestUserDTO.getUsername());

        if (userOptional.isPresent() && passwordEncoder.matches(requestUserDTO.getPassword(), userOptional.get().getPassword())) {
            String token = jwtUtil.generateToken(requestUserDTO.getUsername());
    
            return Optional.of(token);
        }

        return Optional.empty();
    }
}
