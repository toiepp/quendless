package com.hyberlet.quendless.service;

import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> userList() {
        return userRepository.findAll();
    }

    public String createUser(User user) {
        User existing = userRepository.findUserByLogin(user.getLogin()).orElse(null);
        if (existing != null)
            return "user already exists";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "ok";
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return userRepository.findUserByLogin(principal.getUsername()).orElse(null);
    }
}
