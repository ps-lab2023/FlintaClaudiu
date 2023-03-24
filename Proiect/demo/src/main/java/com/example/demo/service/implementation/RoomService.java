package com.example.demo.service.implementation;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.RoomServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService implements RoomServiceInterface {

    @Autowired
    RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {this.roomRepository = roomRepository;}

    @Override
    public Boolean deleteRoom(Room room)
    {
        roomRepository.deleteById(room.getId());
        if(roomRepository.existsById(room.getId()))
            return false;
        return true;
    }

    @Override
    public Room addRoom(Room room)
    {
        roomRepository.save(room);

        return room;
    }

    @Override
    public Room updatePricePerNight(Room room, Float price)
    {
        Room updatedRoom = roomRepository.findRoomById(room.getId());
        if (updatedRoom != null) {
            updatedRoom.setPricePerNight(price);
            roomRepository.save(updatedRoom);
        }

        return  updatedRoom;
    }
}
