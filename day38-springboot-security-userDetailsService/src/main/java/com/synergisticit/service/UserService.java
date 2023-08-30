package com.synergisticit.service;

import com.synergisticit.domain.User;

public interface UserService {
    User save(User user);
    User findById(long userId);
    Iterable<User> findAll();
    void deleteById(long userId);
    
    User findUserByUsername(String username);
}
