package com.example.classactivity3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SuccessActivity extends AppCompatActivity {
    private TextView city_name;
    private ArrayList<Weather> weathers;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Intent intent = getIntent();
        city_name = findViewById(R.id.city_name);
        recyclerView = findViewById(R.id.recyclerView);

        try{
            JSONObject jsonObj = new JSONObject(intent.getStringExtra("data"));
            JSONObject titleObj = jsonObj.getJSONObject("city");
            city_name.setText(titleObj.getString("name") + ", " + titleObj.getString("country"));

            JSONArray list = jsonObj.getJSONArray("list");
            weathers = new ArrayList<>();
            for (int i = 0; i < list.length(); i++) {
                JSONObject weatherObj = list.getJSONObject(i);
                String date_time = weatherObj.getString("dt_txt");
                String date = date_time.substring(0, date_time.indexOf(" "));
                String time = date_time.substring(date_time.indexOf(" ") + 1);
                String temp = weatherObj.getJSONObject("main").getString("feels_like");
                String description = weatherObj.getJSONArray("weather").getJSONObject(0).getString("description");
                Weather weather = new Weather(date, time, temp, description);
                weathers.add(weather);

                // create weather adapter to pass in the data
                WeatherAdapter adapter = new WeatherAdapter(weathers);
                // attach adapter to recyclerview to populate
                recyclerView.setAdapter(adapter);
                // layout manager
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
