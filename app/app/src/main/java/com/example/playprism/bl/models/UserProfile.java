package com.example.playprism.bl.models;

import java.util.List;

public class UserProfile {
    private String id;
    private String nickname;
    private String email;
    private String password;
    private String image;
    private int role;
    private List<String> orders;
    private List<String> giveaways;
    private List<String> wonGiveaways;

    public void setId(String id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    public void setGiveaways(List<String> giveaways) {
        this.giveaways = giveaways;
    }

    public void setWonGiveaways(List<String> wonGiveaways) {
        this.wonGiveaways = wonGiveaways;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public int getRole() {
        return role;
    }

    public List<String> getOrders() {
        return orders;
    }

    public List<String> getGiveaways() {
        return giveaways;
    }

    public List<String> getWonGiveaways() {
        return wonGiveaways;
    }
}
