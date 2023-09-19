package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Flight;
import com.synergisticit.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired FlightRepository flightRepository;
    
    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight getById(Long id) {
        Optional<Flight> opt = flightRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return flightRepository.existsById(id);
    }

}
