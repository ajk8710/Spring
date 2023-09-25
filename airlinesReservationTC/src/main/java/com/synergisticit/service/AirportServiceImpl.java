package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airport;
import com.synergisticit.repository.AirportRepository;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired AirportRepository airportRepository;
    
    @Override
    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport getById(Long id) {
        Optional<Airport> opt = airportRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return airportRepository.existsById(id);
    }

}
