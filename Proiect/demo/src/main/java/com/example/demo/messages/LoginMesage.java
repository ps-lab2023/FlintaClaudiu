package com.example.demo.messages;

import enums.UserType;

public class LoginMesage {
    String message;
    Boolean status;
    UserType type;
    String username;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginMesage(String message, Boolean status, UserType type, String username) {
        this.message = message;
        this.status = status;
        this.type = type;
        this.username = username;
    }
}

