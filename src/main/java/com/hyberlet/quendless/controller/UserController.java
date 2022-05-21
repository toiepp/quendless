package com.hyberlet.quendless.controller;

import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.userList();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user/register")
    public String registration(@RequestBody User user) {
        if (user == null)
            return "user not presented";
        System.out.println("Name: '" + user.getLogin() + "' Password: '" + user.getPassword() + "'");
        if (Objects.equals(user.getLogin(), ""))
            return "user has empty login";
        return userService.createUser(user);
    }

    @GetMapping("/user")
    public User getCurrentUser(HttpServletRequest request) {
        System.out.println(Arrays.toString(request.getCookies()));
//        Principal principal = request.getUserPrincipal();
//        System.out.println(principal.getName());
        User user = userService.getCurrentUser();
        return user;
    }

    @PutMapping("/user/{user_id}")
    public String editQueue(@PathVariable long user_id) {
        // todo: realise
        return null;
    }

    @DeleteMapping("/user/{user_id}")
    public String deleteUser(@PathVariable long user_id) {
        // todo: realise
        return null;
    }

}
