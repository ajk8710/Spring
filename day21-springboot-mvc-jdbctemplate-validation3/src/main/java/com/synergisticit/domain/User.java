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

@Table(name = "User4") // tag @Table if want to use another name other than the default (on database)
@Component  // flag as Spring Bean, so that it can be injected into other beans using @Autowired
@Entity  // flag as JPA or Spring Data managed bean to be read/write to database
public class User {
    
    // @NotNull is needed even if @Id is present. @NotNull is for validation purposes and @ID is for db purposes.
    @NotNull(message="must have userid (msg from tag)")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userid;
    
    @NotEmpty(message="must have username (msg from tag)")
    private String username;
    
    @NotEmpty(message="must have password (msg from tag)")
    private String password;
    
    @NotEmpty(message="must have email (msg from tag)")
    private String email;
    
    @NotEmpty(message="must have mobile (msg from tag)")
    private String mobile;
    
    @ManyToMany  // Many to Many relationship
    @JoinTable(name="user_role",  // creates join table
        joinColumns = {@JoinColumn(name="userid")},
        inverseJoinColumns = {@JoinColumn(name="roleId")}
    )
    
    private Set<Role> roles = new HashSet<>();
    
    public User() {}

    public User(Long userid, String username, String password, String email, String mobile) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
    }
    
    public User(Long userid, String username, String password, String email, String mobile, HashSet<Role> roles) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.roles = roles;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User [userid=" + userid + ", username=" + username + ", email=" + email + ", mobile=" + mobile + "]";
    }
    
}
