package com.synergisticit.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;

@Order(value=2)
@Component
public class CommandLineRunnerImpl1 implements CommandLineRunner {
    @Autowired UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        List<User> users = userRepository.findAll();
        
        for (User user : users) {
            System.out.println(user.getUsername() + ", " + user.getEmail() + ", " + user.getMobile());
        }
        
    }

}
