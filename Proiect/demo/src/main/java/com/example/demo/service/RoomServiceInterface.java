package com.example.demo.service;

import com.example.demo.model.Room;
import org.springframework.stereotype.Component;

@Component
public interface RoomServiceInterface {

    public Boolean deleteRoom(Room room);
    public Room addRoom(Room room);
    public Room updatePricePerNight(Room room, Float price);
}
