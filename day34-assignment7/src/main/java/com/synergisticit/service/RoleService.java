package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleService {
    Role saveRole(Role role);
    Role findRoleById(long roleId);
    List<Role> findAllRoles();
    void deleteRoleById(long roleId);
    boolean existByRoleId(long roleId);
}
