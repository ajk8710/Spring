package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Airport;

public interface AirportService {
    Airport save(Airport airport);
    Airport getById(Long id);
    List<Airport> getAll();
    void deleteById(Long id);
    boolean existById(Long id);
}
