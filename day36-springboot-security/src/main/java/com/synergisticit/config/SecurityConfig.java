package com.synergisticit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new InMemoryUserDetailsManager();
        
        /*
        // {noop} is to bypass encryption requirement
        var user1 = User.withUsername("pikachu").password("{noop}pikachu").authorities("User", "Admin").build();
        var user2 = User.withUsername("raichu").password("{noop}raichu").authorities("User", "Admin").build();
        var user3 = User.withUsername("charmander").password("{noop}charmander").authorities("User").build();
        var user4 = User.withUsername("squirtle").password("{noop}squirtle").authorities("Developer").build();
        */
        
        var user1 = User.withUsername("pikachu").password(passwordEncoder().encode("pikachu")).authorities("User", "Admin").build();
        var user2 = User.withUsername("raichu").password(passwordEncoder().encode("raichu")).authorities("User", "Admin").build();
        var user3 = User.withUsername("charmander").password(passwordEncoder().encode("charmander")).authorities("User").build();
        var user4 = User.withUsername("squirtle").password(passwordEncoder().encode("squirtle")).authorities("Developer").build();

        
        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);
        userDetailsService.createUser(user3);
        userDetailsService.createUser(user4);
        
        return userDetailsService;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
}
