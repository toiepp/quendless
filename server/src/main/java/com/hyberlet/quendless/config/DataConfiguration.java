package com.hyberlet.quendless.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableJpaRepositories("com.hyberlet.quendless.repository")
@EnableAspectJAutoProxy
@EnableAsync
@EnableScheduling
@EnableJdbcHttpSession
@EnableWebMvc
public class DataConfiguration {

}
