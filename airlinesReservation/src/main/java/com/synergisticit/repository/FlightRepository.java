package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    
    // query data by method name using JpaRepository nomenclature. field name must match.
    List<Flight> findByDepartureCity_airportIdAndArrivalCity_airportId(long departureId, long arrivalId);
}
