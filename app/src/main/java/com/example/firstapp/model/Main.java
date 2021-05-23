package com.example.firstapp.model;

public class Main {
    public Main(float temp, float feels_like, Integer pressure, Integer humidity) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.pressure = pressure;
        this.humidity = humidity;
    }

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
