package com.github.amoraes.helloservice;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-service", fallback = WeatherServiceClientFallback.class)
public interface WeatherServiceClient {

    @GetMapping("/v1/weather")
    @CachePut(cacheNames = "weather", key = "#p0", unless = "#result == null")
    public String getTemperature(@RequestParam("location") String location);

}
