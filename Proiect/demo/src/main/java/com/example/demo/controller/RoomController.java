package com.example.demo.controller;

import com.example.demo.DTO.RoomDTO;
import com.example.demo.service.implementation.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:3000", methods = {
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT,
        RequestMethod.GET})
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomDTO addUser(@RequestBody RoomDTO roomDTO) {
        return roomService.addRoom(roomDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteRoomById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return roomService.deleteRoom(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean updatePricePerNight(@RequestParam Long id, Float price){ return roomService.updatePricePerNight(id,price);}

    @GetMapping()
    public List<RoomDTO> findAllRooms() {

        return roomService.findAllRooms();
    }


}
