package com.fiipractic.configuration;

import com.fiipractic.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitAppConfig {
    @Bean
    CommandLineRunner initUser(UserService userService) {
        return args -> {
            userService.createUser("Andrei", "AndreiSeJoaca34", "password123");
        };
    }
}
