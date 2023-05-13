package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import enums.UserStatus;
import enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Include
    private Long id;

    @ToString.Include
    @Column(length = 50)
    @Size(min = 4, max = 50)
    private String firstName;

    @ToString.Include
    @Column(length = 50)
    @Size(min = 4, max = 50)
    private String lastName;

    @Column(length = 50)
    @Size(min = 4, max = 50)
    private String userName;

    private Long telephone;

    @Email(message = "invalid email adress")
    private String email;

    @Column(length = 50)
    @Size(min = 2, max = 150)
    private String password;

    @NotNull
    private UserType type;

    @NotNull
    private UserStatus status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "user")
    private List<Reservation> reservationList = new ArrayList<>();

}
