package com.synergisticit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.dao.RoleDao;
import com.synergisticit.domain.Role;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired RoleDao roleDao;

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(long roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public Role update(long roleId) {
        return roleDao.update(roleId);
    }

    @Override
    public void deleteById(long roleId) {
        roleDao.deleteById(roleId);
    }

}
