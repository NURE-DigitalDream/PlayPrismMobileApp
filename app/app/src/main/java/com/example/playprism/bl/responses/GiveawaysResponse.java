package com.example.playprism.bl.responses;

import com.example.playprism.bl.models.Product;
import com.example.playprism.bl.models.UserProfile;

import java.util.List;

public class GiveawaysResponse {
    private String id;
    private Product product;
    private List<UserProfile> participants;
    private UserProfile winner;
    private String expirationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<UserProfile> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserProfile> participants) {
        this.participants = participants;
    }

    public UserProfile getWinner() {
        return winner;
    }

    public void setWinner(UserProfile winner) {
        this.winner = winner;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
