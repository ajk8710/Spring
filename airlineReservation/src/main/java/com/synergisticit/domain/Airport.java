package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Airport {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long airportId;
    
    private String airportCity;
    
    private String airportCode;
    
    @OneToMany
    private List<Flight> depatureFlights = new ArrayList<>();  // must indicate Hibernate what concrete class of list you want
    
    @OneToMany
    private List<Flight> arrivalFlights = new ArrayList<>();
    
}
