package com.synergisticit.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
    
    @OneToOne  // one reservation has one passenger
    private Passenger passenger;
    
    @OneToOne  // one reservation has one flight
    private Flight flight;
    
    private Boolean checkedIn;
    
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate issuedDate;
    
}
