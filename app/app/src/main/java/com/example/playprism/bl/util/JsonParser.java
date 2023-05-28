package com.example.playprism.bl.util;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.playprism.bl.models.GiveawaysItem;
import com.example.playprism.bl.models.UserData;
import com.example.playprism.bl.responses.GiveawaysJsonResponse;
import com.example.playprism.bl.responses.GiveawaysResponse;
import com.example.playprism.bl.responses.UserJsonResponse;
import com.example.playprism.bl.services.RequestManager;
import com.google.gson.Gson;

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
}
