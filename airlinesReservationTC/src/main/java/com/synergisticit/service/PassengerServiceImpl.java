package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Passenger;
import com.synergisticit.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired PassengerRepository passengerRepository;
    
    @Override
    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger getById(Long id) {
        Optional<Passenger> opt = passengerRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public List<Passenger> getAll() {
        return passengerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return passengerRepository.existsById(id);
    }

}
