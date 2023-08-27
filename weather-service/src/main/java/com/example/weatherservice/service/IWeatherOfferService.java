package com.example.weatherservice.service;

import com.example.weatherservice.model.dto.request.WeatherOfferRequestDto;
import com.example.weatherservice.model.dto.response.ResponseDto;
import com.example.weatherservice.model.dto.response.WeatherResponseDto;
import com.example.weatherservice.model.entity.WeatherOffer;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IWeatherOfferService {
    WeatherResponseDto getWeatherDataForOffer(WeatherOffer request) throws JsonProcessingException;
    List<WeatherOffer> getAllWeatherOffer();
    ResponseDto saveWeatherOffer(WeatherOfferRequestDto request) throws JsonProcessingException;
}
