package com.example.firstapp.model;

public class Weather {
    public Weather(String main, String description) {
        this.main = main;
        this.description = description;
    }

    private String main;
    private String description;



    public String getDescription() {
        return description;
    }

    public String getMain() {
        return main;
    }
}
