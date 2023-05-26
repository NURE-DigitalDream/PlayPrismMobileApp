package com.example.playprism.models;

import com.example.playprism.ui.giveaways.GiveawayStatus;

import java.util.Date;

public class GiveawaysItem {
    private String id;
    private String title;
    private String category;
    private String platform;
    private String yearOfRelease;
    private String developerCompany;
    private String genres;
    private Date expirationDate;
    private GiveawayStatus status;

    public GiveawaysItem(String id, String title, String category, String platform, String yearOfRelease, String developerCompany, String genres, GiveawayStatus status) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.platform = platform;
        this.yearOfRelease = yearOfRelease;
        this.developerCompany = developerCompany;
        this.genres = genres;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public GiveawayStatus getStatus() {
        return status;
    }

    public void setStatus(GiveawayStatus status) {
        this.status = status;
    }

    public String getDeveloperCompany() {
        return developerCompany;
    }

    public void setDeveloperCompany(String developerCompany) {
        this.developerCompany = developerCompany;
    }
}
