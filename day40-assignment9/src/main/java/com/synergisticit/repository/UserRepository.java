package com.synergisticit.repository;

import java.util.List;

import com.synergisticit.domain.User;

public interface UserRepository {
    
    public abstract User save(User user);
    public abstract User findById(long userid);
    public abstract List<User> findAll();
    public abstract User update(User user);
    public abstract void deleteById(long userid);

}
