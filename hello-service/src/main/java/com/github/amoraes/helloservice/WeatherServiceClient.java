package com.github.amoraes.helloservice;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-service")
public interface WeatherServiceClient {

    @GetMapping("/v1/weather/temperature")
    @CachePut(cacheNames = "temperature", key = "#p0", unless = "#result == null")
    String getTemperature(@RequestParam("location") String location);

}