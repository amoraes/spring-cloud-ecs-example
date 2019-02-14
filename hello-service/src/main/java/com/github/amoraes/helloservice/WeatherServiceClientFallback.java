package com.github.amoraes.helloservice;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class WeatherServiceClientFallback implements WeatherServiceClient {

    @Override
    public String getTemperature(String location) {
        location = location.toLowerCase();
        switch (location) {
            case "sydney":
                return randomTemperature(20,35);
            case "brisbane":
                return randomTemperature(25,40);
            case "perth":
                return randomTemperature(40,50);
            default:
                return randomTemperature(10,25);

        }
    }

    private String randomTemperature(int min, int max) {
        int temperature = new Random().nextInt((max - min) + 1) + min;
        return String.format("%d.0ºC", temperature);
    }
}