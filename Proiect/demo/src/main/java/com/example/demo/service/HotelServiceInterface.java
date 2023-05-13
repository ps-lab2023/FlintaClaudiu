package com.example.demo.service;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.RoomDTO;
import com.example.demo.messages.ReviewMessage;
import com.example.demo.model.Hotel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HotelServiceInterface {

    List<HotelDTO> findHotelsByCountry(String country);
    List<HotelDTO> findHotelsByRegion(String region);
    List<HotelDTO> findHotelsByNoOfFreeRoomsGraterThanOrEqual(Long number);
    Boolean deleteHotel(Long id);
    HotelDTO addHotel(HotelDTO hotel);
    Hotel decrementNoOfFreeRooms(Hotel hotel);
    HotelDTO findHotelById(Long id);
    List<HotelDTO> findAllHotels();
    List<RoomDTO> findAllRooms(Long id);
    List<HotelDTO> findHotelsByCountryAndMaxNoOfGuests(String country, Long maxNoOfGuests);
    Hotel findHotelById2(Long id);
    ReviewMessage addReview(String email, Long reservationId, Long grade);
}
