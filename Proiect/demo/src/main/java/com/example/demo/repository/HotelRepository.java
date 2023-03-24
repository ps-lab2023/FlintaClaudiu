package com.example.demo.repository;

import com.example.demo.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

    List<Hotel> findAllByCountry(String country);
    List<Hotel> findAllByRegion(String region);
    List<Hotel> findHotelsByNoOfFreeRoomsIsGreaterThanEqual(Long value);
    Hotel findHotelById(Long id);

}
