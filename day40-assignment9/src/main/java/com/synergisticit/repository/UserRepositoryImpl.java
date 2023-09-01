package com.synergisticit.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
    
    @Autowired JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        if (this.findById(user.getUserid()) == null) {  // if user not exists, create user
            String sql = "INSERT INTO USER31AUG (userid, username, dob, email) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, user.getUserid(), user.getUsername(), user.getDob(), user.getEmail());
        }
        else {  // if user exists, update user
            String sql = "UPDATE USER31AUG SET username=?, dob=?, email=? WHERE userid=?";
            jdbcTemplate.update(sql, user.getUsername(), user.getDob(), user.getEmail(), user.getUserid());
        }
        return user;
    }

    @Override
    public User findById(long userid) {
        String sql = "SELECT * FROM USER31AUG WHERE userid=?";
        try {
            User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), userid);
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USER31AUG";
        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        return users;
    }

    @Override
    public User update(User user) {
        if (this.findById(user.getUserid()) == null) {  // if user not exists, return null
            return null;
        }
        else {  // if user exists, update user
            String sql = "UPDATE USER31AUG SET username=?, dob=?, email=? WHERE userid=?";
            jdbcTemplate.update(sql, user.getUsername(), user.getDob(), user.getEmail(), user.getUserid());
        }
        return user;
    }

    @Override
    public void deleteById(long userid) {
        String sql = "DELETE FROM USER31AUG WHERE userid=?;";
        jdbcTemplate.update(sql, userid);
    }

}
