package com.example.playprism.bl.services;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RequestManager {
    public static final String PREFS_NAME = "UserPreferences";
    public static final String COOKIE_KEY = "CookieRefreshToken";
    public static final String USER_RESPONSE = "UserResponse";

    public static void makeRequest(Context context, String url, JSONObject params, ResponseCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        CookieJsonObjectRequest jsonObjectRequest = new CookieJsonObjectRequest(
                Request.Method.POST,
                url,
                params,
                response -> {
                    // Convert JSONObject to String
                    String responseString = response.toString();

                    Log.i("Response", responseString);

                    // Save responseString into SharedPreferences
                    SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(USER_RESPONSE, responseString);
                    editor.apply();

                    // Invoke the callback with the response
                    callback.onResponse(responseString);
                },
                callback::onError,
                context
        );

        requestQueue.add(jsonObjectRequest);
    }

    static class CookieJsonObjectRequest extends JsonObjectRequest {
        private Context context;

        public CookieJsonObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Context context) {
            super(method, url, jsonRequest, listener, errorListener);
            this.context = context;
        }

        @Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
            // Get cookies from headers and save it
            String rawCookies = response.headers.get("Set-Cookie");
            if (rawCookies != null) {
                Log.i("Cookies", rawCookies);
                // Save cookies into SharedPreferences
                SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(COOKIE_KEY, rawCookies);
                editor.apply();
            } else {
                Log.i("Cookies", "No cookies in the response");
            }

            return super.parseNetworkResponse(response);
        }
    }

    public static void makeGetRequest(Context context, String url, ResponseCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    // Convert JSONObject to String
                    String responseString = response.toString();

                    Log.i("Giveaway Response", responseString);

                    // Invoke the callback with the response
                    callback.onResponse(responseString);
                },
                callback::onError
        );

        requestQueue.add(jsonObjectRequest);
    }

}


