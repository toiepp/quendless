package com.hyberlet.quendless.service;

import com.hyberlet.quendless.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.hyberlet.quendless.model.User user = userRepository.findUserByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Username: %s not found", username))
        );
        return new User(user.getLogin(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
