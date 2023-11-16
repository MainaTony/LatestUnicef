package com.example.mediawatch.ApiResponse;

public class User {
    private float id;
    private String username;
    private String fullName;
    private float intrash;
    private String dateCreated;
    private float organisationIdFk;
    private String password;
    private float accessChannelId;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public float getIntrash() {
        return intrash;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public float getOrganisationIdFk() {
        return organisationIdFk;
    }

    public String getPassword() {
        return password;
    }

    public float getAccessChannelId() {
        return accessChannelId;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setIntrash(float intrash) {
        this.intrash = intrash;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setOrganisationIdFk(float organisationIdFk) {
        this.organisationIdFk = organisationIdFk;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccessChannelId(float accessChannelId) {
        this.accessChannelId = accessChannelId;
    }
}
