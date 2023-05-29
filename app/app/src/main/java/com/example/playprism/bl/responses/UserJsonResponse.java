package com.example.playprism.bl.responses;

import com.example.playprism.bl.models.UserData;

public class UserJsonResponse {

    private UserData data;
    private String error;

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}