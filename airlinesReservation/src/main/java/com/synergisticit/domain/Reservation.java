package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long ticketNum;
    
    @JsonIgnore  // JsonIgnore for rest controller
    @OneToOne  // one reservation has one passenger
    private Passenger passenger;
    
    @JsonIgnore  // JsonIgnore for rest controller
    @OneToOne  // one reservation has one flight
    private Flight flight;
    
    private Boolean checkedIn = false;  // by default is false
    
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate issuedDate;
    
}
