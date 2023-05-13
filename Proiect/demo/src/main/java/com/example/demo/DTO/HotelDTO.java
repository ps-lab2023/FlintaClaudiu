package com.example.demo.DTO;

import com.example.demo.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDTO {

    private Long id;

    private String name;

    private String country;

    private String region;

    private String address;

    private Long phone;

    private String email;

    private Long noOfRooms;

    private Long noOfFreeRooms;

    private String image;

    private Long rating;

    private Long maxNoOfGuests;

    private Float grade;

    private List<Reservation> reservationList;

}
