package com.github.amoraes.weatherservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
public class WeatherEndpoint {

    private ExternalWeatherService externalWeatherService;

    public WeatherEndpoint(ExternalWeatherService externalWeatherService) {
        this.externalWeatherService = externalWeatherService;
    }

    @GetMapping
    public ResponseEntity<String> weather(String location) {
        String celsius = externalWeatherService.getTemperatureInCelsius(location);
        if (celsius != null) {
            return ResponseEntity.ok(celsius);
        }
        return ResponseEntity.notFound().build();
    }

}
