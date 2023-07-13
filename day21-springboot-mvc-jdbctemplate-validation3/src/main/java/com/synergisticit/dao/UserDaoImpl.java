package com.synergisticit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired JdbcTemplate jdbcTemplate;
    
    @Override
    public User save(User user) {
        // System.out.println(user.getUserid() +"\n" + user.getUsername() +"\n" + user.getEmail() +"\n" + user.getMobile());
        
        if (this.findById(user.getUserid()) == null) {  // if user not exists, create user
            String sql = "INSERT INTO USER4 (userid, username, email, mobile) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, user.getUserid(), user.getUsername(), user.getEmail(), user.getMobile());
        }
        else {  // if user exists, update user
            String sql = "UPDATE USER4 SET username=?, email=?, mobile=? WHERE userid=?";
            jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getMobile(), user.getUserid());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USER4";
        List<User> users = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
        return users;
    }

    @Override
    public User findById(long userid) {
        String sql = "SELECT * FROM USER4 WHERE userid=?";
        try {
            User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), userid);
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User update(long userid) {
        String sql = "SELECT * FROM USER4 WHERE userid=?";
        try {
            User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class), userid);
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void deleteById(long userid) {
        String sql = "DELETE FROM USER4 WHERE userid=?;";
        jdbcTemplate.update(sql, userid);
    }

}
