package com.synergisticit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.synergisticit.domain.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
    
    @Autowired JdbcTemplate jdbcTemplate;

    @Override
    public Role save(Role role) {
        
        if (this.findRoleById(role.getRoleId()) == null) {  // if user not exists, create user
            String sql = "insert into role (roleid, name) values (?, ?)";
            int recordsAffected = jdbcTemplate.update(sql, role.getRoleId(), role.getName());
            // this also works -- jdbcTemplate.update(sql, new Object[] {role.getRoleId(), role.getName()});
            System.out.println("recordsAffected: " + recordsAffected);
        }
        else {  // if user exists, update user
            String sql = "update role set name=? where roleid=?";
            jdbcTemplate.update(sql, role.getName(), role.getRoleId());
        }
        return role;
    }

    @Override
    public Role findRoleById(long roleId) {
        String sql = "select * from role where roleid=?";
        try {
            Role role = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Role.class), roleId);
            return role;
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("no role found by roleId");
            return null;
        }
    }

    @Override
    public List<Role> findAll() {
        String sql = "select * from role";
        List<Role> roles = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Role.class));
        for (Role r : roles) {
            System.out.println(r);
        }
        return roles;
    }

    @Override
    public void deleteRoleById(long roleId) {
        String sql = "delete from role where roleid=?;";
        int recordsAffected = jdbcTemplate.update(sql, roleId);
        System.out.println("recordsAffected: " + recordsAffected);
    }

    @Override
    public Long maxRoleId() {
        String sql = "select max(roleId) from role";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

}
