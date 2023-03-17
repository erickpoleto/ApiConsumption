package com.example.apiconsumption.models;


public class User {
    Integer id;
    String username;
    String email;

    public User(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
