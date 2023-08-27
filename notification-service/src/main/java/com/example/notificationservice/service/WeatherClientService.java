package com.example.notificationservice.service;

import com.example.notificationservice.model.dto.request.WeatherOfferRequestDto;
import com.example.notificationservice.model.dto.response.WeatherResponseDto;
import com.example.notificationservice.model.entity.WeatherOffer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@FeignClient(name = "weather-service",url = "http://localhost:8080/")
public interface WeatherClientService {

    @GetMapping("/weather-offer")
    List<WeatherOffer> getAllWeatherOffer();

    @PostMapping("/weather-offer")
    WeatherResponseDto getWeatherDataForOffer(@RequestBody WeatherOffer weatherOffer);

    @PostMapping("/weather-data")
    WeatherResponseDto getWeatherData(@RequestBody String city);

}
