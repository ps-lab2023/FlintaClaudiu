package com.example.demo.service.implementation;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReservationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReservationService implements ReservationServiceInterface {

    @Autowired
    ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {this.reservationRepository = reservationRepository;}

    @Override
    public Boolean deleteReservation(Reservation reservation)
    {
        reservationRepository.deleteById(reservation.getId());
        if(reservationRepository.existsById(reservation.getId()))
            return false;
        return true;
    }

    @Override
    public Reservation addReservation(Reservation reservation)
    {
        reservationRepository.save(reservation);

        return reservation;
    }

    @Override
    public Reservation updateReservationInterval(Reservation reservation, Date start, Date end)
    {
        Reservation updatedReservation =  reservationRepository.findReservationById(reservation.getId());
        if(updatedReservation != null) {
            updatedReservation.setStartDate(start);
            updatedReservation.setEndDate(end);
            reservationRepository.save(updatedReservation);
        }

        return updatedReservation;
    }

}
