package com.example.demo.mapper;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public static HotelDTO mapModelToDto(Hotel hotel){

        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setName(hotel.getName());
        hotelDTO.setCountry(hotel.getCountry());
        hotelDTO.setRegion(hotel.getRegion());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setPhone(hotel.getTelefon());
        hotelDTO.setEmail(hotel.getEmail());
        hotelDTO.setNoOfRooms(hotel.getNoOfRooms());
        hotelDTO.setNoOfFreeRooms(hotel.getNoOfFreeRooms());
        hotelDTO.setImage(hotel.getImage());
        hotelDTO.setRating(hotel.getRating());
        hotelDTO.setMaxNoOfGuests(hotel.getMaxNoOfGuests());
        hotelDTO.setGrade(hotel.getGrade());
        hotelDTO.setReservationList(hotel.getReservationList());

        return hotelDTO;
    }

    public static Hotel mapDtoToModel(HotelDTO hotelDTO){
        Hotel hotel=new Hotel();

        hotel.setId(hotelDTO.getId());
        hotel.setName(hotelDTO.getName());
        hotel.setCountry(hotelDTO.getCountry());
        hotel.setRegion(hotelDTO.getRegion());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setTelefon(hotelDTO.getPhone());
        hotel.setEmail(hotelDTO.getEmail());
        hotel.setNoOfRooms(hotelDTO.getNoOfRooms());
        hotel.setNoOfFreeRooms(hotelDTO.getNoOfFreeRooms());
        hotel.setImage(hotelDTO.getImage());
        hotel.setRating(hotelDTO.getRating());
        hotel.setMaxNoOfGuests(hotelDTO.getMaxNoOfGuests());
        hotel.setGrade(hotelDTO.getGrade());
        hotel.setReservationList(hotelDTO.getReservationList());

        return hotel;
    }
}
