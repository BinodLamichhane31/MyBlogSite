package com.example.myblogsite.security;

import com.example.myblogsite.entity.User;
import com.example.myblogsite.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class PasswordUpdater {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordUpdater(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void updatePasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            String rawPassword = user.getPassword(); // assuming you have a way to get the raw password
            String encodedPassword = passwordEncoder.encode(rawPassword);
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
    }
}

