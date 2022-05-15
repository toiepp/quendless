package com.hyberlet.quendless.service;

import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> userList() {
        return userRepository.findAll();
    }
}
