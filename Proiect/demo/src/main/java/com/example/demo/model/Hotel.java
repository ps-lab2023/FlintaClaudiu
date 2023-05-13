package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@ToString(onlyExplicitlyIncluded = true)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Include
    private Long id;

    @ToString.Include
    private String name;

    @Column(length = 50)
    private String country;

    @Column(length = 50)
    @Size(min = 4, max = 50)
    private String region;

    @Column(length = 90)
    private String address;

    private Long telefon;

    @Email(message = "invalid email adress")
    private String email;

    @Min(0)
    @Max(100)
    private Long noOfRooms;

    @Min(0)
    @Max(100)
    private Long noOfFreeRooms;

    private String image;

    @Min(0)
    @Max(6)
    private Long rating;

    @Min(0)
    @Max(100)
    private Long maxNoOfGuests;

    private Float grade;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hotel", orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "hotel")
    private List<Reservation> reservationList = new ArrayList<>();

    public void removeRoom(Long id){
        for(Room r : rooms){
            if(r.getId() == id){
                rooms.remove(r);
                break;
            }
        }
    }
}
