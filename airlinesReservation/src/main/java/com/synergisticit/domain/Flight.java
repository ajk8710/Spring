package com.synergisticit.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
    // @JoinColumn(name="airportId")  // causes error due to duplicate join column
    @ManyToOne  // there are many departure flight on one airport
    private Airport departureCity;
    
    // @JoinColumn(name="airportId")  // causes error due to duplicate join column
    @ManyToOne  // there are many arrival flight on one airport
    private Airport arrivalCity;
    
    private Double ticketPrice;
    
    private int capacity;
    
    private int booked;  // how many are booked out of capacity
    
    @DateTimeFormat(iso = ISO.DATE)  // need because there are so many types of date format
    private LocalDate departureDate;
    
    @DateTimeFormat(iso = ISO.TIME)
    private LocalTime departureTime;
    
    @JoinColumn(name="airlinesId")  // not required, it's joined by primary key by default
    @ManyToOne  // there are many flight on one airline
    private Airlines operatingAirlines;
    
    @JsonIgnore  // JsonIgnore for rest controller
    @OneToMany(mappedBy="flight")  // mapped by Reservation's flight attribute
    private List<Reservation> reservationsOfFlight = new ArrayList<>();
    
}
