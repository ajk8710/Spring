package com.synergisticit.service;

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
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long userid) {
        Optional<User> optUser = userRepository.findById(userid);  // findById on JpaRepository returns Optional<User> or Optional with default EMPTY value if not exists
        
        if (optUser.isPresent()) {
            return optUser.get();
        }
        return null;
    }

    @Override
    public User update(long userid) {
        return userRepository.getReferenceById(userid);  // getReferenceById throws exception if not exists
    }

    @Override
    public void deleteById(long userid) {
        userRepository.deleteById(userid);
    }
    
    @Override
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }

}
