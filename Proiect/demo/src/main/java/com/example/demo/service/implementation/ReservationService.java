package com.example.demo.service.implementation;

import com.example.demo.DTO.ReservationDTO;
import com.example.demo.DTO.ReservationXML;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ReservationServiceInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService implements ReservationServiceInterface {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationMapper reservationMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ReservationService(ReservationRepository reservationRepository) {this.reservationRepository = reservationRepository;}

    public Boolean setReservationsDetailToNull(Long id){

        Reservation r =  reservationRepository.findReservationById(id);
        if(r != null) {
            r.setHotel(null);
            r.setRoom(null);
            r.setUser(null);
            reservationRepository.save(r);
            return true;
        }
        return false;

    }

    @Override
    @Transactional
    public Boolean deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);

        if (reservation != null) {
            Room room = reservation.getRoom();
            User user = reservation.getUser();
            Hotel hotel = reservation.getHotel();

            room.setReservationList(null);
            user.setReservationList(null);
            hotel.getReservationList().remove(reservation);

            entityManager.flush();
            System.out.println("Am facut flush");
            reservationRepository.delete(reservation);

            return true;
        }
        return false;
    }




    @Override
    public Reservation addReservation(ReservationDTO reservationDTO)
    {
        Reservation reservation = reservationRepository.save(reservationMapper.mapDtoToModel(reservationDTO));
        return reservation;
    }

    @Override
    public Reservation updateReservationInterval(Reservation reservation, Date start, Date end)
    {
        Reservation updatedReservation =  reservationRepository.findReservationById(reservation.getId());
        if(updatedReservation != null) {
            updatedReservation.setStartDate(start);
            updatedReservation.setEndDate(end);
            reservationRepository.save(updatedReservation);
        }

        return updatedReservation;
    }

    @Override
    public List<ReservationDTO> findReservations(String email)
    {
        User u = userRepository.findUserByEmail(email);
        if(u!= null) {
           return u.getReservationList() .stream().map(
                   ReservationMapper::mapModelToDto
           ).collect(Collectors.toList());
        }

        return null;
    }

    public void generateXml(ReservationXML reservationDto) {
        try {

            JAXBContext context = JAXBContext.newInstance(ReservationXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(reservationDto, new File("reservation.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
