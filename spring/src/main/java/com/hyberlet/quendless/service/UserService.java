package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @LoggedAction
    public List<User> userList() {
        return userRepository.findAll();
    }

    @LoggedAction
    public String createUser(User user) {
        User existing = userRepository.findUserByLogin(user.getLogin()).orElse(null);
        if (existing != null)

            return "User already exists";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "ok";
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User defaultUser = new User();
        defaultUser.setLogin(username);
        return userRepository.findUserByLogin(username).orElse(defaultUser);
    }
}
