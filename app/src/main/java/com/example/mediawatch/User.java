package com.example.mediawatch;

public class User {
    private String userId;
    private String username;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}


