package com.github.amoraes.weatherservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExternalWeatherService {

    @Value("${weather.api.url}")
    private String externalApiUrl;

    @Value("${weather.api.key}")
    private String externalApiKey;

    private final RestTemplate restTemplate;

    public ExternalWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getTemperatureInCelsius(String location) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("location", location);
        queryParams.put("key", externalApiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(externalApiUrl, WeatherResponse.class, queryParams);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            Double tempInKelsius = Double.parseDouble(response.getBody().main.temp);
            Double tempInCelsius = tempInKelsius - 273.15;
            return String.format("%.1fºC", tempInCelsius);
        }
        return null;
    }

}

class WeatherResponse {
    public WeatherResponseMain main;
}

class WeatherResponseMain {
    public String temp;
}