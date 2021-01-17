package com.example.randomadvice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.randomadvice.models.AdviceSlip;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;


public class MainActivity extends AppCompatActivity {
    public static final String RANDOMADVICE = "https://api.adviceslip.com/advice";
    public static final String TAG = "MainActivity";

    AdviceSlip adviceSlip;
    TextView tvAdvice;
    Button btnGetAdvice;

    AsyncHttpClient client;
    JSONObject slip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Layout Elements
        tvAdvice = findViewById(R.id.tvAdvice);
        btnGetAdvice = findViewById(R.id.btnGetAdvice);



        btnGetAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HTTP Client
                client = new AsyncHttpClient();
                client.get(RANDOMADVICE, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Headers headers, JSON json) {
                        //Get the JSON object. In this case we are getting just one object and not
                        JSONObject jsonObject = json.jsonObject; //Top level JSON
                        Log.i(TAG, "JSON object: " + jsonObject.toString());
                        try {
                            slip = jsonObject.getJSONObject("slip"); //Slip JSON object
                            try {
                                String advice = slip.getString("advice");
                                tvAdvice.setText(advice);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.i(TAG, "slip object: " + slip.toString());
                            Log.i(TAG, "slip advice: " + slip.getString("advice"));



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                        Log.d(TAG, "JSON Request Failed");
                    }
                });
            }
        });
    }
}