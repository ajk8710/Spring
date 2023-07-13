package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.User;

public interface UserService {
    
    public abstract User save(User user);
    public abstract List<User> findAll();
    public abstract User findById(long userid);
    public abstract User update(long userid);
    public abstract void deleteById(long userid);
    
}
