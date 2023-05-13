package com.example.demo.mapper;

import com.example.demo.DTO.ReservationDTO;
import com.example.demo.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public static ReservationDTO mapModelToDto(Reservation reservation) {

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setUser(reservation.getUser());
        reservationDTO.setRoom(reservation.getRoom());
        reservationDTO.setHotel(reservation.getHotel());
        reservationDTO.setHotelAddress(reservation.getHotel().getAddress());
        reservationDTO.setHotelName(reservation.getHotel().getName());
        reservationDTO.setUserEmail(reservation.getUser().getEmail());

        return reservationDTO;
    }

    public static Reservation mapDtoToModel(ReservationDTO reservationDTO) {

        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setUser(reservationDTO.getUser());
        reservation.setRoom(reservationDTO.getRoom());
        reservation.setHotel(reservationDTO.getHotel());

        return reservation;
    }
}
