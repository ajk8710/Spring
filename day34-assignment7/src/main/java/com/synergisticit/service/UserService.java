package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.User;

public interface UserService {
    User saveUser(User user);
    User findUserById(long userId);
    List<User> findAllUsers();
    void deleteUserById(long userId);
    boolean existByUserId(long userId);
}
