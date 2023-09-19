package com.synergisticit.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Flight {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long flightId;
    
    private String flightNum;
    
    @NotEmpty
    private String departureCity;
    
    @NotEmpty
    private String arrivalCity;
    
    private Double ticketPrice;
    
    private int capacity;
    
    private int booked;  // how many are booked out of capacity
    
    @DateTimeFormat(iso = ISO.DATE)  // need because there are so many types of date format
    private LocalDate departureDate;
    
    @DateTimeFormat(iso = ISO.TIME)
    private LocalTime departureTime;
    
    @ManyToOne  // there are many flight on one airline
    private Airlines operatingAirlines;
    
}
