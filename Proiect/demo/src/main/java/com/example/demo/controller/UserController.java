package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.messages.LoginMesage;
import com.example.demo.model.User;
import com.example.demo.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", methods = {
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT,
        RequestMethod.GET,})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PostMapping("/login")
    public LoginMesage login(@RequestBody UserDTO userDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return userService.LogIn(userDTO.getEmail(),userDTO.getPassword());
    }

    @PutMapping("/logout")
    public Boolean logout(@RequestParam String email) {
        return userService.logout(email);
    }

    @PutMapping("/change")
    public Boolean changePass(@RequestParam String email, String oldPass, String newPass) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return userService.changePassword(email, oldPass,newPass);
    }

    @GetMapping()
    public List<UserDTO> findAllUsers() {

        return userService.findAllUsers();
    }
}
