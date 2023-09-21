package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleService {
    Role save(Role role);
    Role findRoleByID(long roleId);
    List<Role> findAll();
    void deleteRoleById(long roleId);
    
}
