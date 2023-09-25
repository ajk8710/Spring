package com.synergisticit.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    
    @JsonIgnore  // JsonIgnore for rest controller
    @OneToMany(mappedBy="departureCity")
    private List<Flight> depatureFlights = new ArrayList<>();  // must indicate Hibernate what concrete class of list you want
    
    @JsonIgnore  // JsonIgnore for rest controller
    @OneToMany(mappedBy="arrivalCity")
    private List<Flight> arrivalFlights = new ArrayList<>();
    
}
