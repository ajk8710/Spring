package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleService {
    public abstract Role save(Role role);
    public abstract List<Role> findAll();
    public abstract Role findById(Long roleId);
    public abstract void deleteById(Long roleId);
}
