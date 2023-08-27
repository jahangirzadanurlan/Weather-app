package com.example.weatherservice.controller;

import com.example.weatherservice.model.dto.request.WeatherOfferRequestDto;
import com.example.weatherservice.model.dto.response.ResponseDto;
import com.example.weatherservice.model.dto.response.WeatherResponseDto;
import com.example.weatherservice.model.entity.WeatherOffer;
import com.example.weatherservice.service.WeatherClientService;
import com.example.weatherservice.service.impl.WeatherOfferService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeatherRestController {
    private final WeatherOfferService weatherOfferService;
    private final WeatherClientService weatherClientService;

    @GetMapping("/weather-offer")
    public List<WeatherOffer> getAllWeatherOffer(){
        return weatherOfferService.getAllWeatherOffer();
    }

    @PostMapping("/weather-offer")
    public WeatherResponseDto getWeatherDataForOffer(@RequestBody WeatherOffer weatherOffer) throws JsonProcessingException {
        return weatherOfferService.getWeatherDataForOffer(weatherOffer);
    }

    @PostMapping("/weather")
    public ResponseEntity<ResponseDto> saveWeatherOffer(@RequestBody WeatherOfferRequestDto weatherOfferRequestDto){
        ResponseDto response = weatherOfferService.saveWeatherOffer(weatherOfferRequestDto);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/weather-data")
    public WeatherResponseDto getWeatherData(@RequestBody String city) throws JsonProcessingException {
        return weatherClientService.getWeatherData(city);
    }
}
