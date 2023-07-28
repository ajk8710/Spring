package com.synergisticit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired RoleRepository roleRepository;
    
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleByID(long roleId) {
        Optional<Role> optRole = roleRepository.findById(roleId);
        if (optRole.isPresent()) {
            return optRole.get();
        }
        return null;
    }

    @Override
    public List<Role> findAll() {
        Iterable<Role> iterable = roleRepository.findAll();
        Iterator<Role> itr = iterable.iterator();
        
        List<Role> roles = new ArrayList<>();
        while (itr.hasNext()) {
            roles.add(itr.next());
        }
        
        return roles;
    }

    @Override
    public void deleteRoleById(long roleId) {
        roleRepository.deleteById(roleId);
    }

}
