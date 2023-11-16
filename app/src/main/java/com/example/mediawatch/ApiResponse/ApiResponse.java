package com.example.mediawatch.ApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse {
    @JsonProperty("responseCode")
    private String responseCode;
    private String responseDescription;
    Entity EntityObject;

    // Getter Methods

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public Entity getEntity() {
        return EntityObject;
    }

    // Setter Methods

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public void setEntity(Entity entityObject) {
        this.EntityObject = entityObject;
    }
}
