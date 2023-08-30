package com.synergisticit.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="role36")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    
    @NotNull  // There is no NotEmpty for int value
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long roleId;
    
    @NotEmpty  // must be NotEmpty, not NotNull. Otherwise empty string accepted.
    private String name;
    
    // Many to Many relationship
    @ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<>();
    
}
