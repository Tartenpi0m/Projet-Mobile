package com.example.firstapp.model;

public class Wind {
    public Wind(float speed, Integer deg) {
        this.speed = speed;
        this.deg = deg;
    }

    private float speed;
    private Integer deg;

    public float getSpeed() {
        return speed;
    }

    public Integer getDeg() {
        return deg;
    }
}
