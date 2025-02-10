package com.example.diploma.Configs;

import com.example.diploma.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{


    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService detailsService;
    private final JwtProperties jwt;

<<<<<<< HEAD
//    @Beans
=======
//    @Bean
>>>>>>> ea4dd34 (first committed back)
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.
//                csrf().disable()
//                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
//                        .anyRequest().authenticated())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .userDetailsService(detailsService);
//
//        return http.build();
//    }

}
