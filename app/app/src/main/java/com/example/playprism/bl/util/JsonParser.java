package com.example.playprism.bl.util;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.playprism.bl.models.GiveawaysItem;
import com.example.playprism.bl.models.PurchasedItem;
import com.example.playprism.bl.models.UserData;
import com.example.playprism.bl.responses.GiveawaysJsonResponse;
import com.example.playprism.bl.responses.GiveawaysResponse;
import com.example.playprism.bl.responses.UserJsonResponse;
import com.example.playprism.bl.services.RequestManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonParser {

    public static UserData getUser(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(RequestManager.PREFS_NAME, MODE_PRIVATE);

        String responseString = sharedPreferences.getString(RequestManager.USER_RESPONSE, null);

        String rawCookies = sharedPreferences.getString(RequestManager.COOKIE_KEY, null);

        Gson gson = new Gson();
        UserJsonResponse response = gson.fromJson(responseString, UserJsonResponse.class);

        return response.getData();
    }

    public static List<GiveawaysResponse> getGiveaways(String responseString) {
        Gson gson = new Gson();
        GiveawaysJsonResponse response = gson.fromJson(responseString, GiveawaysJsonResponse.class);

        return response.getData();
    }

    public static List<PurchasedItem> getPurchasedItems(String responseString) throws JSONException {

        // Create a JSONObject from the JSON string
        JSONObject jsonObj = new JSONObject(responseString);

        // Get the "data" array from the JSONObject
        JSONArray dataArray = jsonObj.getJSONArray("data");

        List<PurchasedItem> purchasedItems = new ArrayList<>();
        // Loop through each object in the data array
        for (int i = 0; i < dataArray.length(); i++) {
            // Get the current object
            JSONObject dataObj = dataArray.getJSONObject(i);

            // Get and print each field from the data object
            String productId = dataObj.getString("productId");
            String userId = dataObj.getString("userId");
            String name = dataObj.getString("name");
            int rating = dataObj.getInt("rating");
            float price = dataObj.getLong("price");
            String headerImage = dataObj.getString("headerImage");

            String purchaseDate = dataObj.getString("purchaseDate");
            String date = DateConverter.formatDate(purchaseDate);
            Date resEndDate = null;
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            try {
                resEndDate = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String value = dataObj.getString("value");

            purchasedItems.add(new PurchasedItem(name, resEndDate, price));
        }

        return purchasedItems;
    }
}
