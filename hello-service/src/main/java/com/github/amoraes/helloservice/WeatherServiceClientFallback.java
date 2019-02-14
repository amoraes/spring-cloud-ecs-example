package com.github.amoraes.helloservice;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class  WeatherServiceClientFallback implements WeatherServiceClient {

    @Override
    @Cacheable(cacheNames = "weather", key = "#p0")
    public String getTemperature(String location) {
        return "not available";
    }

}