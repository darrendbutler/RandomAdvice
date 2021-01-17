package com.example.randomadvice.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class AdviceSlip {
    int id;
    String advice;

    public AdviceSlip(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        advice = jsonObject.getString("advice");
        Log.i("AdviceSlip class", "Initialization done");
    }

    public int getId() {
        return id;
    }

    public String getAdvice() {
        return advice;
    }
}
