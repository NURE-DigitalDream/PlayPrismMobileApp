package com.example.playprism.bl.responses;

import com.example.playprism.bl.models.UserData;

import java.util.List;

public class GiveawaysJsonResponse {
    List<GiveawaysResponse> data;

    private String error;

    public List<GiveawaysResponse> getData() {
        return data;
    }

    public void setData(List<GiveawaysResponse> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
