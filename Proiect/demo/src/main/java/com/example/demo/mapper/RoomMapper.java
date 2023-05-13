package com.example.demo.mapper;

import com.example.demo.DTO.RoomDTO;
import com.example.demo.model.Room;
import com.example.demo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    @Autowired
    HotelRepository hotelRepository;

    public static RoomDTO mapModelToDto(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setName(room.getName());
        roomDTO.setPricePerNight(room.getPricePerNight());
        roomDTO.setHotel(room.getHotel());
        roomDTO.setImage(room.getImage());
        roomDTO.setCapacity(room.getCapacity());

        return roomDTO;
    }

    public static Room mapDtoToModel(RoomDTO roomDTO) {
        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setName(roomDTO.getName());
        room.setPricePerNight(roomDTO.getPricePerNight());
        room.setHotel(roomDTO.getHotel());
        room.setImage(roomDTO.getImage());
        room.setCapacity(roomDTO.getCapacity());

        return room;
    }
}
