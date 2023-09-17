package com.synergisticit.dao;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleDao {
    
    public abstract Role save(Role role);
    public abstract List<Role> findAll();
    public abstract Role findById(long roleId);
    public abstract Role update(long roleId);
    public abstract void deleteById(long roleId);

}
