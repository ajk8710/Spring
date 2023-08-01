package com.synergisticit.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
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
    private Address customerAddress;
    
    private String realId;
    
    @OneToMany(mappedBy="accountCustomer")
    private List<Account> customerAccounts = new ArrayList<>();
    
    @JoinColumn(name="userId")
    @OneToOne
    private User user;
}
