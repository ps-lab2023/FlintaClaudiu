package com.example.demo.mapper;

import com.example.demo.DTO.UserDTO;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO mapModelToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setType(user.getType());
        userDTO.setStatus(user.getStatus());

        return userDTO;
    }

    public static User mapDtoToModel(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setType(userDTO.getType());
        user.setStatus(userDTO.getStatus());

        return user;
    }
}
