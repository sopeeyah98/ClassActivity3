package com.example.classactivity3;

import android.os.Parcel;
import android.os.Parcelable;

public class Weather {
    // private instance variables
    private String date;
    private String time;
    private String temp;
    private String description;

    // constructor
    public Weather(){

    }

    public Weather(String date, String time, String temp, String description) {
        this.date = date;
        this.time = time;
        this.temp = temp;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setFeels_like(String temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // getters and setters

}
