package com.example.mediawatch.ApiResponse;

public class Entity {
    User UserObject;
    private String token;


    // Getter Methods

    public User getUser() {
        return UserObject;
    }

    public String getToken() {
        return token;
    }

    // Setter Methods

    public void setUser(User userObject) {
        this.UserObject = userObject;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
