package com.synergisticit.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long customerId;
    
    private String customerName;
    
    private String customerGender;
    
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate customerDob;
    
    private String customerMobileNum;
    
    @Embedded
    @Valid
    private Address customerAddress;
    
    private String realId;
    
    @JsonIgnore
    // @JsonBackReference
    // @JsonManagedReference
    @OneToMany(mappedBy="accountCustomer")
    private List<Account> customerAccounts = new ArrayList<>();
    
    @JsonIgnore
    // @JsonBackReference
    @JoinColumn(name="userId")
    @OneToOne
    private User user;
}
