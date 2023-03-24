package com.example.demo.service;

import com.example.demo.model.Hotel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HotelServiceInterface {

    public List<Hotel> findHotelsByCountry(String country);
    public List<Hotel> findHotelsByRegion(String region);
    public List<Hotel> findHotelsByNoOfFreeRoomsGraterThanOrEqual(Long number);
    public Boolean deleteHotel(Hotel hotel);
    public Hotel addHotel(Hotel hotel);
    public Hotel decrementNoOfFreeRooms(Hotel hotel);
}
