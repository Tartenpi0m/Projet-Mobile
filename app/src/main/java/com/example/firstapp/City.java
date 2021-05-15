package com.example.firstapp;

import java.util.List;

public class City {
    private String name;
    private Main main;
    private Wind wind;
    private Clouds clouds;


    private List<Weather> weather;

    public String getName() {
        return name;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public List<Weather> getWeather() {
        return weather;
    }




}
