package com.example.demo.model;

import enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private Long telephone;

    @Email(message = "invalid email adress")
    private String email;

    private String password;

    private UserType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "id")
    private List<Reservation> reservationList = new ArrayList<>();


    // !! mutata in service
    public void addReservation(Reservation reservation)
    {
        this.reservationList.add(reservation);
    }

}
