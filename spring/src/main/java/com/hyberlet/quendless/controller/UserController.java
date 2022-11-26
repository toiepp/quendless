package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.userList();
    }

    @PostMapping("/user/register")
    public String registration(@RequestBody User user, HttpServletResponse response) {
        if (user == null) {
            response.setStatus(400);
            return "user not presented";
        }
        System.out.println(user);
        System.out.println("Name: '" + user.getLogin() + "' Password: '" + user.getPassword() + "'");
        if (Objects.equals(user.getLogin(), "")) {
            response.setStatus(400);
            return "user has empty login";
        }
        String result = userService.createUser(user);
        if (!Objects.equals(result, "ok")) {
            response.setStatus(400);
            return "user already exists";
        }
        return "ok";
    }

    @GetMapping("/user")
    public User getCurrentUser() {
        User user = userService.getCurrentUser();
        return user;
    }

    @PutMapping("/user/{user_id}")
    public String editUser(@PathVariable UUID user_id) {
        // todo: realise
        return null;
    }

    @DeleteMapping("/user/{user_id}")
    public String deleteUser(@PathVariable UUID user_id) {
        // todo: realise
        return null;
    }

}
