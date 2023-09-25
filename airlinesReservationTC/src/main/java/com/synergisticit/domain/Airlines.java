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
public class Airlines {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long airlinesId;
    
    private String airlinesName;
    
    private String airlinesCode;
    
    @JsonIgnore  // JsonIgnore for rest controller
    @OneToMany(mappedBy="operatingAirlines")  // one airlines has many flights
    private List<Flight> flights = new ArrayList<>();
    
}
