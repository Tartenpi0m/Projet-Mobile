package com.example.firstapp;

import java.util.List;

public class City {
    public City(String name, Main main, Wind wind, Clouds clouds, Weather weather, Rain rain) {
        this.name = name;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.weather = weather;
        this.rain = rain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    private String name;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;
    private Weather weather;


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

    public Weather getWeather() {
        return weather;
    }




}
