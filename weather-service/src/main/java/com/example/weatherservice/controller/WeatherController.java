package com.example.weatherservice.controller;

import com.example.weatherservice.model.dto.response.WeatherResponseDto;
import com.example.weatherservice.service.WeatherClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherClientService weatherClientService;

    @GetMapping("/weather")
    public String getWeatherData(@RequestParam(name = "city") String city, Model model) throws JsonProcessingException {
        WeatherResponseDto weatherData = weatherClientService.getWeatherData(city);
        model.addAttribute("weather",weatherData);

        log.info("Weather request.City => {}",city);
        return "weather";
    }

}
