package com.synergisticit.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    @Autowired BCryptPasswordEncoder bCrypt;
    @Autowired UserDetailsService userDetailsService;
    
    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {  // this only exists in memory, not db
        
        List<UserDetails> users = new ArrayList<>();
        
        List<GrantedAuthority> authority1 = new ArrayList<>();
        authority1.add(new SimpleGrantedAuthority("Admin"));
        authority1.add(new SimpleGrantedAuthority("User"));
        
        List<GrantedAuthority> authority2 = new ArrayList<>();
        authority2.add(new SimpleGrantedAuthority("HR"));
        authority2.add(new SimpleGrantedAuthority("User"));
        
        List<GrantedAuthority> authority3 = new ArrayList<>();
        authority3.add(new SimpleGrantedAuthority("Developer"));
        authority3.add(new SimpleGrantedAuthority("User"));
        
        List<GrantedAuthority> authority4 = new ArrayList<>();
        authority4.add(new SimpleGrantedAuthority("User"));
        
        UserDetails user1 = new User("pikachu", bCrypt.encode("pikachu"), authority1);
        users.add(user1);
        
        UserDetails user2 = new User("raichu", bCrypt.encode("raichu"), authority1);
        users.add(user2);
        
        UserDetails user3 = new User("charmander", bCrypt.encode("charmander"), authority2);
        users.add(user3);
        
        UserDetails user4 = new User("squirtle", bCrypt.encode("squirtle"), authority3);
        users.add(user4);
        
        UserDetails user5 = new User("potato", bCrypt.encode("potato"), authority4);
        users.add(user5);
        
        return new InMemoryUserDetailsManager(users);
    }
    */
    
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        // http.authorizeHttpRequests().anyRequest().permitAll();  // this allows to bypass all http security
        
        http.authorizeHttpRequests()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()  // permits access to specified url without login
        .requestMatchers(AntPathRequestMatcher.antMatcher("/home")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/register")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/registerUser")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/createRole")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/home2")).permitAll()  // testing
        .anyRequest().authenticated()  // any url other than above must be authenticated
        .and()
        .httpBasic()  // browser to request log in when accessing pages with authentication required
        .and()
        .formLogin()
        .loginPage("/login")  // url for custom log in page
        .defaultSuccessUrl("/home", true)  // url upon successful log in
        .and()
        .logout()
        .invalidateHttpSession(true)  // invalidate session upon log out
        .clearAuthentication(true)  // clear authentication upon log out
        .logoutRequestMatcher(AntPathRequestMatcher.antMatcher("/logout"))
        .logoutSuccessUrl("/login?logout").permitAll()  // may need to change home.jsp: /logout -> /login?logout
        .and()
        .userDetailsService(userDetailsService);
        return http.build();
    }
    
    
    @Bean
    DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCrypt);
        return authProvider;
    }

}
