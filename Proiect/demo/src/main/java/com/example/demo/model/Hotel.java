package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String country;

    private String region;

    private String address;

    private Long telefon;

    private String email;

    private Long noOfRooms;

    private Long noOfFreeRooms;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "id")
    private List<Room> rooms;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "id")
    private List<Reservation> reservationList = new ArrayList<>();

    public void addReservation(Reservation reservation)
    {
        this.reservationList.add(reservation);
    }


}
