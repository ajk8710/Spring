package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.User;

public interface UserService {
    public User save(User user);
    public List<User> findAll();
    public User findById(long userid);
    public User update(long userid);
    public void deleteById(long userid);
    
    public User findByName(String username);  // custom method name that does not exists in JpaRepository and its parents
}
