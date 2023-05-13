package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.service.implementation.HotelService;
import com.example.demo.service.implementation.ReservationService;
import com.example.demo.service.implementation.RoomService;
import com.example.demo.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = "http://localhost:3000", methods = {
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT,
        RequestMethod.GET})
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;

    @PostMapping("/addres")
    public Reservation addReservation(@RequestParam Long id,
                                         @RequestParam String email,
                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                         @RequestParam Long hotelId
                                         ) {
        Room r = roomService.findRoomById2(id);
        User u = userService.findUserByEmail2(email);
        Hotel h =  hotelService.findHotelById2(hotelId);

        if(u!= null && r != null && h!= null )
        {
            ReservationDTO rr = new ReservationDTO();
            rr.setRoom(r);
            rr.setUser(u);
            rr.setHotel(h);
            rr.setStartDate(startDate);
            rr.setEndDate(endDate);
            Reservation added = reservationService.addReservation(rr);
            ReservationXML rrXML= new ReservationXML(rr.getStartDate(),rr.getEndDate(),rr.getHotel().getAddress());

            reservationService.generateXml(rrXML);

            return added;
        }

        return null;
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteReservationById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        reservationService.deleteReservation(id);
        return reservationService.deleteReservation(id);
    }

    @GetMapping("/findReservations")
    public List<ReservationDTO> findReservationForUser(@RequestParam String email) {

        return reservationService.findReservations(email);
    }

}
