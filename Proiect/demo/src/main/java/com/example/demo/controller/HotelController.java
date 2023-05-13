package com.example.demo.controller;

import com.example.demo.DTO.HotelDTO;
import com.example.demo.DTO.RoomDTO;
import com.example.demo.messages.ReviewMessage;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.service.implementation.HotelService;
import com.example.demo.service.implementation.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/hotel")
@CrossOrigin(origins = "http://localhost:3000", methods = {
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT,
        RequestMethod.GET})
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<HotelDTO> findAllHotels() {

        return hotelService.findAllHotels();
    }

    @GetMapping("/rooms/{id}")
    public List<RoomDTO> findAllRooms(@PathVariable Long id) {

        return hotelService.findAllRooms(id);
    }

    @GetMapping("/rooms/dates")
    public List<RoomDTO> findAllRoomsByDates( @RequestParam Long id,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                              @RequestParam Long noMaxOfGuests ) {

        List<RoomDTO> list = hotelService.findAllRooms(id);
        List<RoomDTO> finalList = new ArrayList<>();
        for(RoomDTO room : list){
            System.out.println("Id-ul camerei " + room.getId());
            Room r = roomService.findRoomById2(room.getId());
            boolean ok = false;
            if(room.getCapacity() >= noMaxOfGuests) {
                System.out.println(r.getReservationList());
                if(!r.getReservationList().isEmpty()) {
                    for (Reservation res : r.getReservationList()) {
                        if (endDate.before(res.getStartDate())) {
                            System.out.println("Caz 1 ");

                        }
                        else if (startDate.after(res.getEndDate())) {
                            System.out.println("Caz 2 ");

                        }
                        else {
                            System.out.println("Caz 3 ");
                            ok = true;
                            break;
                        }
                    }
                }
                }else{
                ok=true;
            }
            if(ok == false)
            {
                finalList.add(room);
            }
        }
        System.out.println(finalList);
        return finalList;
    }

    @GetMapping("/{country}/country")
    public List<HotelDTO> findHotelByCountry(@PathVariable String country) {
        System.out.println(country);
        return hotelService.findHotelsByCountry(country);
    }

    @GetMapping("/{region}/region")
    public List<HotelDTO> findHotelByRegion(@PathVariable String region) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        System.out.println(region);
        return hotelService.findHotelsByRegion(region);
    }

    @GetMapping("/regionAndGuests")
    public List<HotelDTO> findHotelByRegionAndNoOfGuests( @RequestParam String region,
                                                          @RequestParam Long noMaxOfGuests) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        System.out.println(region);
        System.out.println(noMaxOfGuests);
        return hotelService.findHotelsByCountryAndMaxNoOfGuests(region, noMaxOfGuests);
    }

    @PostMapping("/rating")
    public ReviewMessage addReview(@RequestParam String email,
                                   @RequestParam Long reservationId,
                                   @RequestParam Long grade
                                   ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        System.out.println(email);
        System.out.println(reservationId);
        System.out.println(grade);
        return hotelService.addReview(email , reservationId, grade);
    }
    

    @GetMapping("/{id}/id")
    public HotelDTO findHotelById(@PathVariable Long id) {
        return hotelService.findHotelById(id);
    }


    @GetMapping("/{id}/reservation")
    public List<Reservation> getReservationList(@PathVariable Long id) {
        return hotelService.findHotelById(id).getReservationList();
    }

    @GetMapping("/{id}/room")
    public List<Room> getRoomsList(@PathVariable Long id) {
        return hotelService.findHotelById2(id).getRooms();
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteHotelById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return hotelService.deleteHotel(id);
    }

    @PostMapping("/add")
    public HotelDTO addHotel(@RequestBody HotelDTO hotelDTO) {
        return hotelService.addHotel(hotelDTO);
    }

}
