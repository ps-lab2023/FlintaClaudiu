package com.example.demo.service.implementation;

import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.HotelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements HotelServiceInterface{

    @Autowired
    HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {this.hotelRepository = hotelRepository;}

    @Override
    public List<Hotel> findHotelsByCountry(String country)
    {
        return hotelRepository.findAllByCountry(country);
    }

    @Override
    public List<Hotel> findHotelsByRegion(String region)
    {
        return hotelRepository.findAllByRegion(region);
    }

    @Override
    public List<Hotel> findHotelsByNoOfFreeRoomsGraterThanOrEqual(Long number)
    {
        return hotelRepository.findHotelsByNoOfFreeRoomsIsGreaterThanEqual(number);
    }

    /**
     *
     * @param hotel
     * @return true if hotel was deleted from table, false if not
     */
    @Override
    public Boolean deleteHotel(Hotel hotel)
    {
        hotelRepository.deleteById(hotel.getId());
        if(hotelRepository.existsById(hotel.getId()))
            return false;
        return true;
    }

    @Override
    public Hotel addHotel(Hotel hotel){ hotelRepository.save(hotel); return hotel;}

    @Override
    public Hotel decrementNoOfFreeRooms(Hotel hotel)
    {
        System.out.println("Aici");
        Hotel updatedHotel = hotelRepository.findHotelById(hotel.getId());
        if (updatedHotel != null) {
            updatedHotel.setNoOfFreeRooms((updatedHotel.getNoOfFreeRooms() - 1));
            hotelRepository.save(updatedHotel);
        }
        return updatedHotel;
    }

}
