package com.synergisticit.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Entity
@Table(name="user36")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    
    @NotNull  // @NotNull is needed even if @Id is present. @NotNull is for validation purposes and @ID is for db purposes
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    @NotEmpty
    private String email;
    
    @NotEmpty
    private String mobile;
    
    // @NotEmpty
    @ManyToMany  // Many to Many relationship
    @JoinTable(name="user_role36",  // creates join table
        joinColumns = {@JoinColumn(name="userid")},
        inverseJoinColumns = {@JoinColumn(name="roleId")}
    )
    private Set<Role> roles = new HashSet<>();
    
}
