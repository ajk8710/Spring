package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long userId;
    
    private String username;
    
    private String password;
    
    @JsonBackReference
    @JoinTable(name="user_role",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private List<Role> roles = new ArrayList<>();
    
}
