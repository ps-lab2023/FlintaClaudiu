package com.example.demo.DTO;

import enums.UserStatus;
import enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long telephone;

    private String password;

    private UserType type;

    private UserStatus status;

}
