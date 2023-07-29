package com.synergisticit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired UserRepository userRepository;
    
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        
        if (optUser.isPresent()) {
            return optUser.get();
        }
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        Iterable<User> iterable = userRepository.findAll();
        Iterator<User> iterator = iterable.iterator();
        
        List<User> users = new ArrayList<>();
        while (iterator.hasNext()) {
            users.add(iterator.next());
        }
        return users;
    }

    @Override
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean existByUserId(long userId) {
        return userRepository.existsById(userId);
    }

}
