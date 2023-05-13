package com.example.demo.DTO;

import com.example.demo.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {

    private Long id;

    private String name;

    private Float pricePerNight;

    private Long hotelId;

    private String image;

    private Long capacity;

    private Hotel hotel;
}
