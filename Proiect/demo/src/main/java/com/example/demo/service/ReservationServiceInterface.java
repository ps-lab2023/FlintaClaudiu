package com.example.demo.service;

import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface ReservationServiceInterface {

    Boolean deleteReservation(Long id);
    Reservation addReservation(ReservationDTO reservationDTO);
    Reservation updateReservationInterval(Reservation reservation, Date start, Date end);
    List<ReservationDTO> findReservations(String email);

}
