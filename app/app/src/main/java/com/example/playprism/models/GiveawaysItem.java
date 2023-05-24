package com.example.playprism.models;

public class GiveawaysItem {
    private String title;
    private String category;
    private String platform;

    public GiveawaysItem(String title, String category, String platform) {
        this.title = title;
        this.category = category;
        this.platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getPlatform() {
        return platform;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
