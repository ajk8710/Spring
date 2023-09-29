package com.synergisticit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    @Autowired BCryptPasswordEncoder bCrypt;
    @Autowired UserDetailsService userDetailsService;
    
    @Bean
    DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCrypt);
        return authProvider;
    }
    
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        
//        // http.authorizeHttpRequests().anyRequest().permitAll();  // this allows to bypass all http security
//        
//        http.authorizeHttpRequests()
//            .requestMatchers("/")
//            .permitAll()
//            .and()
//            .formLogin().permitAll();
//        
//        return http.build();
//    }
    
}
