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
        // TODO Auto-generated method stub
        return roleDao.save(role);
    }

    @Override
    public Role findRoleById(long roleId) {
        return roleDao.findRoleById(roleId);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void deleteRoleById(long roleId) {
        roleDao.deleteRoleById(roleId);
    }

    @Override
    public Long maxRoleId() {
        return roleDao.maxRoleId();
    }

}
