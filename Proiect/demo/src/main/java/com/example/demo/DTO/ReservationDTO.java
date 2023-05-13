package com.example.demo.DTO;

import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDTO {
    private Long id;

    private Date startDate = new Date(0,0,0,0,0);

    private Date endDate = new Date(0,0,0,0,0);

    private User user;

    private Room room;

    private Hotel hotel;

    private String userEmail;

    private Long roomId;

    private String hotelName;

    private String hotelAddress;

}
