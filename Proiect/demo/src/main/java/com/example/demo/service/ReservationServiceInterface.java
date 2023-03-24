package com.example.demo.service;

import com.example.demo.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface ReservationServiceInterface {

    public Boolean deleteReservation(Reservation reservation);
    public Reservation addReservation(Reservation reservation);
    public Reservation updateReservationInterval(Reservation reservation, Date start, Date end);

}
