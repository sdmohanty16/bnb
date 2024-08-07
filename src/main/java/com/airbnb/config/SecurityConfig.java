package com.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
           HttpSecurity http
    ) throws Exception {

        //h(cd)2
        http.csrf().disable().cors().disable(); //Authentication

        //haap
        http.authorizeHttpRequests().anyRequest().permitAll(); //Authorization
        return http.build();
    }
}
