package com.hyberlet.quendless.service;

import com.hyberlet.quendless.aspect.LoggedAction;
import com.hyberlet.quendless.controller.exceptions.AccessDeniedException;
import com.hyberlet.quendless.controller.exceptions.BadRequestException;
import com.hyberlet.quendless.model.User;
import com.hyberlet.quendless.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PermissionService permissionService;

    @LoggedAction
    public List<User> userList() {
        User user = getCurrentUser();
        if (!permissionService.isAdmin(user))
            throw new AccessDeniedException("access denied");
        return userRepository.findAll();
    }

    @LoggedAction
    public void createUser(User user) {
        User existing = userRepository.findUserByLogin(user.getLogin()).orElse(null);
        if (existing != null)
            throw new BadRequestException("User already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        if (Objects.equals(user.getLogin(), "admin")) {
            permissionService.createPermission(user, "all", null, "admin", null);
        }
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

    public Optional<User> findUserById(UUID userId) {
        return userRepository.findById(userId);
    }
}
