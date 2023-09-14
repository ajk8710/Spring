package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Passenger;

public interface PassengerService {
    Passenger save(Passenger passenger);
    Passenger getById(Long id);
    List<Passenger> getAll();
    void deleteById(Long id);
    boolean existById(Long id);
}
