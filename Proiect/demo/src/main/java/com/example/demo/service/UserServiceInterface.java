package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserServiceInterface {

    User LogIn(String userName, String password);
    User findByUserName(String username);
    User findUserById(Long id);
    User updateUserPassword(User user, String password);
    Boolean deleteUser(User user);
    User addUser(User user);

}
