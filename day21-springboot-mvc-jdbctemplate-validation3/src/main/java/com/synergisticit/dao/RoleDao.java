package com.synergisticit.dao;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleDao {
    Role save(Role role);
    Role findRoleById(long roleId);
    List<Role> findAll();
    void deleteRoleById(long roleId);
    Long maxRoleId();
}
