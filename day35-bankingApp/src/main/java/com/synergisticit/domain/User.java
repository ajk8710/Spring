package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class User {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long userId;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    @JoinTable(name="user_role",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private List<Role> roles = new ArrayList<>();
    
}
