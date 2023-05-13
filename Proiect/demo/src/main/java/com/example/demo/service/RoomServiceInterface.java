package com.example.demo.service;

import com.example.demo.DTO.RoomDTO;
import com.example.demo.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoomServiceInterface {

    Boolean deleteRoom(Long id);
    RoomDTO addRoom(RoomDTO roomDTO);
    Boolean updatePricePerNight(Long id, Float price);
    List<RoomDTO> findAllRooms();
    RoomDTO findRoomById(Long id);
    Room findRoomById2(Long id);
}
