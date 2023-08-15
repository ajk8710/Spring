package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Branch {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long branchId;
    
    private String branchName;
    
    @Embedded
    @Valid
    private Address branchAddress;
    
    @OneToMany(mappedBy="accountBranch")
    private List<Account> branchAccounts = new ArrayList<>();
}
