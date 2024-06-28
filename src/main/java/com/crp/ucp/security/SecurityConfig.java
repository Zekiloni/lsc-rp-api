package com.crp.ucp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@EnableWebSecurity
//@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/public/**").permitAll() // Public endpoints
//                .antMatchers("/account/**").authenticated() // Account endpoints require authentication
//                .anyRequest().permitAll() // All other requests
//                .and()
//                .formLogin().permitAll() // Enable form login
//                .and()
//                .httpBasic() // Enable basic auth
//                .and()
//                .csrf().disable(); // Disable CSRF for simplicity
//
//        return http.build();
//    }
//}