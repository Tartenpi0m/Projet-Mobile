package com.example.firstapp;

public class main {
    private float temp;
    private float feels_like;
    private Integer pressure;
    private Integer humidity;

    public float getTemp() {
        return temp - 273.5f;
    }

    public float getFeels_like() {
        return feels_like;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }


}
