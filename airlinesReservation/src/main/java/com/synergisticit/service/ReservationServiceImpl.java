package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Reservation;
import com.synergisticit.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired ReservationRepository reservationRepository;
    
    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getById(Long id) {
        Optional<Reservation> opt = reservationRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return reservationRepository.existsById(id);
    }

}
