package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.messages.LoginMesage;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserServiceInterface {

    LoginMesage LogIn(String userName, String password);
    User findByUserName(String username);
    User findUserById(Long id);
    User updateUserPassword(User user, String password);
    Boolean deleteUser(User user);
    User addUser(UserDTO userDTO);
    UserDTO findUserByEmail(String email);
    User findUserByEmail2(String email);
    Boolean changePassword(String username, String password, String newPassword);
    Boolean logout(String email);
    List<UserDTO> findAllUsers();

}
