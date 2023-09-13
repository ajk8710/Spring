package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Passenger {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long passengerId;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String phone;
    
    private String gender;
    
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dob;
    
    @Enumerated(EnumType.STRING)  // without it, it goes for index number of enum
    private IdentificationType identificationType;
    
}
