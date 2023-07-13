package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.dao.UserDao;
import com.synergisticit.domain.User;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired UserDao userDao;

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(long userid) {
        return userDao.findById(userid);
    }

    @Override
    public User update(long userid) {
        return userDao.update(userid);
    }

    @Override
    public void deleteById(long userid) {
        userDao.deleteById(userid);
    }

}
