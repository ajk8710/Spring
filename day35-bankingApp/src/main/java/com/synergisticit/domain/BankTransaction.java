package com.synergisticit.domain;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class BankTransaction {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long bankTransactionId;
    
    @JoinColumn(name = "toAccountId") // This column references the primary key of the Account table.
    private Long bankTransactionToAccount;
    
    @JoinColumn(name = "fromAccountId") // This column references the primary key of the Account table.
    private Long bankTransactionFromAccount;
    
    private double transactionAmount;
    
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime bankTransactionDateTime;
    
    private String comments;
    
}
