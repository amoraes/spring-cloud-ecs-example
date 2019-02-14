package com.github.amoraes.helloservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("weather-service")
public interface WeatherServiceClient {

    @GetMapping("/v1/weather")
    public String getTemperature(@RequestParam("location") String location);

}
