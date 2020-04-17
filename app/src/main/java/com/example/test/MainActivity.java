package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    Button click, goToMap;
    public TextView data;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.data);
        click = findViewById(R.id.click);
        goToMap = findViewById(R.id.go_to_map);

        mQueue = Volley.newRequestQueue(this);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParsing();
            }
        });
        goToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });

    }

    private void jsonParsing() {
        String url = "https://www.vikisoftware.com/jsonTest";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String companyName = response.getString("companyName");
                            String location = response.getString("location");
                            String drive = response.getString("drive");
                            String latitude = response.getString("latitude");
                            String longitude = response.getString("longitude");

                            data.append(companyName + "\n" +location + "\n" +drive + "\n" +latitude + "\n" +longitude + "\n");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}
