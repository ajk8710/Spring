package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne  // Reservation has one passenger. Passenger can have many reservations
    private Passenger passenger;
    
    @JsonIgnore  // JsonIgnore for rest controller
    @ManyToOne  // Reservation has one flight. Flight can have many reservations.
    private Flight flight;
    
    private Boolean checkedIn = false;  // by default is false
    
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate issuedDate;
    
    // constructor without id: id to be filled in by JpaRepository's save method (auto increment)
    public Reservation(Passenger passenger, Flight flight, Boolean checkedIn, LocalDate issuedDate) {
        this.passenger = passenger;
        this.flight = flight;
        this.checkedIn = checkedIn;
        this.issuedDate = issuedDate;
    }
    
}
