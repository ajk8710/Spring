package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Role {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long roleId;
    
    private String roleName;
    
    @ManyToMany(mappedBy="roles")
    private List<User> users = new ArrayList<>();
}