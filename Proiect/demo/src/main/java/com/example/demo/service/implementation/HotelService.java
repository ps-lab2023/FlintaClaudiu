package com.example.demo.service.implementation;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.RoomDTO;
import com.example.demo.mapper.HotelMapper;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.messages.ReviewMessage;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.HotelServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService implements HotelServiceInterface{

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    HotelMapper hotelMapper;

    public HotelService(HotelRepository hotelRepository) {this.hotelRepository = hotelRepository;}

    @Override
    public List<HotelDTO> findHotelsByCountry(String country)
    {
        return hotelRepository.findAllByCountry(country).stream().map(
            HotelMapper::mapModelToDto
    ).collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> findHotelsByCountryAndMaxNoOfGuests(String region, Long maxNoOfGuests)
    {
        return hotelRepository.findAllByRegionAndMaxNoOfGuestsGreaterThanEqual(region,maxNoOfGuests).stream().map(
                HotelMapper::mapModelToDto
        ).collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> findAllRooms(Long id)
    {
        Hotel h = hotelRepository.findFirstById(id);
        if(h != null) {
            return h.getRooms().stream().map(
                    RoomMapper::mapModelToDto
            ).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<HotelDTO> findHotelsByRegion(String region)
    {
        return hotelRepository.findAllByRegion(region).stream().map(
                HotelMapper::mapModelToDto
        ).collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> findHotelsByNoOfFreeRoomsGraterThanOrEqual(Long number)
    {
        return hotelRepository.findHotelsByNoOfFreeRoomsIsGreaterThanEqual(number).stream().map(
                HotelMapper::mapModelToDto
        ).collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return true if hotel was deleted from table, false if not
     */
    @Override
    @CrossOrigin(origins = "http://localhost:3000")
    public Boolean deleteHotel(Long id)
    {
        hotelRepository.deleteById(id);
        return true;
    }

    @Override
    public HotelDTO addHotel(HotelDTO hotelDTO){
        hotelDTO.setNoOfRooms(0L);
        hotelDTO.setNoOfFreeRooms(0L);
        hotelDTO.setMaxNoOfGuests(0L);
        hotelDTO.setGrade(0.0f);
        Hotel hotel = hotelRepository.save(hotelMapper.mapDtoToModel(hotelDTO));
        return hotelMapper.mapModelToDto(hotel);
    }

    @Override
    public Hotel decrementNoOfFreeRooms(Hotel hotel)
    {
        Hotel updatedHotel = hotelRepository.findHotelById(hotel.getId());
        if (updatedHotel != null) {
            updatedHotel.setNoOfFreeRooms((updatedHotel.getNoOfFreeRooms() - 1));
            hotelRepository.save(updatedHotel);
        }
        return updatedHotel;
    }

    @Override
    public List<HotelDTO> findAllHotels() {
        return hotelRepository.findAll()
                .stream().map(
                        HotelMapper::mapModelToDto
                ).collect(Collectors.toList());
    }

    @Override
    public HotelDTO findHotelById(Long id)
    {
        final Hotel hotel = hotelRepository.findHotelById(id);
        if (hotel != null) {
            return HotelMapper.mapModelToDto(hotel);
        } else return null;
    }

    @Override
    public Hotel findHotelById2(Long id)
    {
        return hotelRepository.findHotelById(id);
    }


    @Override
    public ReviewMessage addReview(String email, Long reservationId, Long grade) {

        Reservation reservation = reservationRepository.findReservationById(reservationId);
        if(reservation != null)
        {
            if(reservation.getUser().getEmail().equals(email)){

                Date currentDate= new Date();

                System.out.println(currentDate);

                System.out.println(reservation.getEndDate());

                if(reservation.getEndDate().before(currentDate)){

                    Hotel hotel = hotelRepository.findHotelById(reservation.getHotel().getId());

                    if(hotel!= null){
                        if(hotel.getGrade() != 0.0){
                            hotel.setGrade((hotel.getGrade()+Float.valueOf(grade))/2.0f);
                        }else{
                            hotel.setGrade(Float.valueOf(grade));
                        }
                        int roundedGrade = Math.round(hotel.getGrade());
                        hotel.setRating(Long.valueOf(roundedGrade));
                        hotelRepository.save(hotel);
                        return new ReviewMessage("Thanks for review",true);

                    }else {
                        return new ReviewMessage("Internal H ERROR",false);
                    }

                }else{
                    return new ReviewMessage("Cannot make review for future reservations",false);
                }

            }else {
                return new ReviewMessage("Given reservation does not correspond to this email",false);
            }

        }
        else {
            return new ReviewMessage("No reservation with given id", false);
        }
    }
}
