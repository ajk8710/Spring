package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Table(name = "User31Aug") // tag @Table if want to use another name other than the default (on database)
@Component  // flag as Spring Bean, so that it can be injected into other beans using @Autowired
@Entity  // flag as JPA or Spring Data managed bean to be read/write to database
public class User {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull
    @Id
    private Long userid;
    
    @NotEmpty
    private String username;
    
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate dob;
    
    @NotEmpty
    private String email;
    
    public User() {}

    public User(Long userid, String username, LocalDate dob, String email) {
        this.userid = userid;
        this.username = username;
        this.dob = dob;
        this.email = email;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
