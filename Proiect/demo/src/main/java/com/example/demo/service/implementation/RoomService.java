package com.example.demo.service.implementation;

import com.example.demo.DTO.RoomDTO;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.RoomServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService implements RoomServiceInterface {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository) {this.roomRepository = roomRepository; this.hotelRepository = hotelRepository;}

    @Override
    public Boolean deleteRoom(Long id)
    {
        Room room = roomRepository.findRoomById(id);
        if(room != null) {
            if (room.getHotel() != null) {

                this.removeRoomRelation(roomMapper.mapModelToDto(room));
            }
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public RoomDTO removeRoomRelation(RoomDTO roomDTO)
    {
        Hotel hotel = hotelRepository.findHotelById(roomDTO.getHotel().getId());
        if(hotel != null) {
            roomDTO.setHotel(null);
            hotel.removeRoom(roomDTO.getId());
            roomRepository.save(roomMapper.mapDtoToModel(roomDTO));
        }
        return roomDTO;
    }

    @Override
    public RoomDTO addRoom(RoomDTO roomDTO)
    {
        Hotel hotel = hotelRepository.findHotelById(roomDTO.getHotelId());

        System.out.println(hotel);
        if(hotel != null) {
            if(hotel.getMaxNoOfGuests() < roomDTO.getCapacity())
            {
                //fac update hotelului
                hotel.setMaxNoOfGuests(roomDTO.getCapacity());
                hotel.setNoOfRooms((hotel.getNoOfRooms()+1));
                hotel.setNoOfFreeRooms((hotel.getNoOfFreeRooms()+1));
            }
            else{
                hotel.setNoOfRooms((hotel.getNoOfRooms()+1));
                hotel.setNoOfFreeRooms((hotel.getNoOfFreeRooms()+1));

            }
            hotelRepository.save(hotel);
            //ii setez camerei hotelul
            roomDTO.setHotel(hotel);
            roomRepository.save(roomMapper.mapDtoToModel(roomDTO));
        }
        return roomDTO;
    }

    @Override
    public Boolean updatePricePerNight(Long id, Float price)
    {
        Room updatedRoom = roomRepository.findRoomById(id);
        System.out.println(updatedRoom.getName());
        if (updatedRoom != null) {
            updatedRoom.setPricePerNight(price);
            roomRepository.save(updatedRoom);
            return true;
        }

        return  false;
    }

    @Override
    public List<RoomDTO> findAllRooms() {
        return roomRepository.findAll()
                .stream().map(
                        RoomMapper::mapModelToDto
                ).collect(Collectors.toList());
    }

    @Override
    public RoomDTO findRoomById(Long id) {
        return  roomMapper.mapModelToDto((roomRepository.findRoomById(id)));
    }

    @Override
    public Room findRoomById2(Long id) {
        return  roomRepository.findRoomById(id);
    }
}
