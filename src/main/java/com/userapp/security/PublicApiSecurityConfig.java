package com.userapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class PublicApiSecurityConfig {

    @Bean
    public SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/public/**") // Only apply to /public/*
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()) //Open to everyone
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}