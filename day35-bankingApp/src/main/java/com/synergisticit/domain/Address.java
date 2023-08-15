package com.synergisticit.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {
    
    @NotEmpty
    private String addressLine1;
    
    private String addressLine2;
    
    @NotEmpty
    private String city;
    
    @NotEmpty
    private String state;
    
    @NotEmpty
    private String country;
    
    @NotEmpty
    private String zipCode;
}
