package com.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class SecurityConfig {

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
           HttpSecurity http
    ) throws Exception {

        //h(cd)2
        http.csrf().disable().cors().disable(); //Authentication

        //haap
        //http.authorizeHttpRequests().anyRequest().permitAll();


        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/createuser","/api/v1/auth/createpropertyowner","/api/v1/auth/login")
                .permitAll()
                .requestMatchers("/api/v1/property/addProperty","/api/v1/property/**").hasRole("OWNER")
                .requestMatchers("/api/v1/auth/createpropertymanager","/api/v1/country/**","/api/v1/city/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated();

        return http.build();
    }
}
