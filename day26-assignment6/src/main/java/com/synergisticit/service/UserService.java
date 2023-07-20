package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.User;

public interface UserService {
    public abstract User save(User user);
    public abstract List<User> findAll();
    public abstract User findById(Long userId);
    public abstract void deleteById(Long userId);
}
