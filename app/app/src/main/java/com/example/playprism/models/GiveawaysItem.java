package com.example.playprism.models;

public class GiveawaysItem {
    private String id;
    private String title;
    private String category;
    private String platform;

    public GiveawaysItem(String id, String title, String category, String platform) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.platform = platform;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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
