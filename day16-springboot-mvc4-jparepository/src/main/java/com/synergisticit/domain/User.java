package com.synergisticit.domain;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Table(name = "User")
@Component
@Entity
public class User {
    
    @Id private Long userid;
    private String username;
    private String email;
    private String mobile;
    
    public User() {}
    
    public User(Long userid, String username, String email, String mobile) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
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

    @Override
    public String toString() {
        return "User [userid=" + userid + ", username=" + username + ", email=" + email + ", mobile=" + mobile + "]";
    }

}
