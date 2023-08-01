package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Account {

    @NotNull
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long accountId;
    
    @NotNull
    private String accountHolder;
    
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate accountDateOpen;
    
    private double accountBalance;
    
    @JoinColumn(name="branchId")
    @ManyToOne
    private Branch accountBranch;
    
    @JoinColumn(name="customerId")
    @ManyToOne
    private Customer accountCustomer;
}
