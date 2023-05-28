package com.example.playprism.bl.models;

import android.graphics.drawable.Drawable;

import java.util.Date;

public class PurchasedItem {
    Drawable icon;
    private String title;
    private Date purchaseDate;
    private float price;

    public PurchasedItem(String title, Date purchaseDate, float price) {
        this.title = title;
        this.purchaseDate = purchaseDate;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public float getPrice() {
        return price;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

