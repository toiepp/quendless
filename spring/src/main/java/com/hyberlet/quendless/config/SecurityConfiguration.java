package com.hyberlet.quendless.config;

import com.hyberlet.quendless.controller.security.AuthFailureHandler;
import com.hyberlet.quendless.controller.security.AuthSuccessHandler;
import com.hyberlet.quendless.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/user/login", "/user/register", "/users")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .failureHandler(authFailureHandler)
                .successHandler(authSuccessHandler)
                .and()
                .userDetailsService(userDetailService)
                .sessionManagement();
    }

}
