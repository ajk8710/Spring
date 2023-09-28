package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Reservation;

public interface ReservationService {
    Reservation save(Reservation reservation);
    Reservation getById(Long id);
    List<Reservation> getAll();
    void deleteById(Long id);
    boolean existById(Long id);
    
    List<Reservation> findByPassengerId(long passengerId);
}
