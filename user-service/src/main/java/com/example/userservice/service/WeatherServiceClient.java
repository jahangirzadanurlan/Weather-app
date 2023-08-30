package com.example.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-service",url = "http://localhost:8080/")
public interface WeatherServiceClient {
    @GetMapping("/weather")
    String getWeatherData(@RequestParam(name = "city") String name);
}
