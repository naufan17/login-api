package com.domain.login_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.login_api.models.User;
import com.domain.login_api.models.dto.UserTokenDTO;
import com.domain.login_api.repository.UserRepository;
import com.domain.login_api.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalStateException("User already exist: " + username);
        }

        User newUser = new User(username, password);
        // newUser.setPassword(passwordEncoder.encode(password));
        
        return userRepository.save(newUser);
    }

    @Override
    public Optional<UserTokenDTO> loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        // if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
        //     String token = jwtUtil.generateToken(username);

        //     UserTokenDTO userTokenDTO = new UserTokenDTO();
        //     userTokenDTO.setUsername(username);
        //     userTokenDTO.setToken(token);
    
        //     return Optional.of(userTokenDTO);
        // }

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            String token = jwtUtil.generateToken(username);

            UserTokenDTO userTokenDTO = new UserTokenDTO();
            userTokenDTO.setUsername(username);
            userTokenDTO.setToken(token);
    
            return Optional.of(userTokenDTO);
        }

        return Optional.empty();
    }
}
