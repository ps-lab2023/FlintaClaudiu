package com.example.demo.repository;

import com.example.demo.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findAllByCountry(String country);
    List<Hotel> findAllByRegion(String region);
    List<Hotel> findHotelsByNoOfFreeRoomsIsGreaterThanEqual(Long value);
    Hotel findHotelById(Long id);
    Hotel findFirstById(Long id);
    List<Hotel> findAllByRegionAndMaxNoOfGuestsGreaterThanEqual(String country, Long maxNoOfGuests);


}
