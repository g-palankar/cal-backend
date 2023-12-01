package com.cal.calbackend.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    @Order(4)
//    SecurityFilterChain configureSession(HttpSecurity http) throws Exception {
////        http.authorizeHttpRequests(authorize -> authorize.anyRequest().session)
//        return http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
//    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.securityMatcher("/users/**")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

//    @Bean
//    @Order(2)
//    SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws Exception {
//        return http.securityMatcher(AntPathRequestMatcher.antMatcher("/h2-console/**"))
//                .authorizeHttpRequests(auth -> auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll())
//                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//                .build();
//    }
//
//    @Bean
//    @Order(1)
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.authorizeHttpRequests(auth -> {
//            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/error/**")).permitAll();
//        }).build();
//    }
}
