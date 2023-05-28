package com.example.playprism.bl.services;

import com.android.volley.VolleyError;

public interface ResponseCallback {
    void onResponse(String response);
    void onError(VolleyError error);
}