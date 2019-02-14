package com.github.amoraes.helloservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/greeting")
public class HelloEndpoint {

    private WeatherServiceClient weatherServiceClient;

    public HelloEndpoint(WeatherServiceClient weatherServiceClient) {
        this.weatherServiceClient = weatherServiceClient;
    }

    @GetMapping
    public ResponseEntity<String> greeting(@RequestParam(name = "name", required = true) String name,
                                           @RequestParam(name = "location") String location) {
        if (location == null) {
            return ResponseEntity.ok(String.format("Hello %s", name));
        } else {
            String temperature = weatherServiceClient.getTemperature(location);
            return ResponseEntity.ok(String.format("Hello %s, temperature now in %s: %s", name, location, temperature));
        }
    }

}
