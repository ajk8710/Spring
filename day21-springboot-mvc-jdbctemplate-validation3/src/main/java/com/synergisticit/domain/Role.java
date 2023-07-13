package com.synergisticit.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
public class Role {
    
    @NotNull(message="must have role id (msg from tag)")  // There is no NotEmpty for int value
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long roleId;
    
    @NotEmpty(message="must have role name (msg from tag)")  // must be NotEmpty, not NotNull. Otherwise empty string accepted.
    private String name;
    
    // Many to Many relationship
    @ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<>();
    
    public Role() {}
    
    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }
    
    public Role(String name) {
        this.name = name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", name=" + name + ", users=" + users + "]";
    }
    
}
