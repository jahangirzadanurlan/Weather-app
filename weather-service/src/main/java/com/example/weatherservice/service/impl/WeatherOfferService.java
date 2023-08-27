package com.example.weatherservice.service.impl;

import com.example.weatherservice.model.dto.request.WeatherOfferRequestDto;
import com.example.weatherservice.model.dto.response.ResponseDto;
import com.example.weatherservice.model.dto.response.WeatherResponseDto;
import com.example.weatherservice.model.entity.WeatherOffer;
import com.example.weatherservice.repository.WeatherOfferRepository;
import com.example.weatherservice.service.IWeatherOfferService;
import com.example.weatherservice.service.WeatherClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherOfferService implements IWeatherOfferService {
    private final WeatherOfferRepository weatherOfferRepository;
    private final WeatherClientService weatherClientService;

    @Override
    public WeatherResponseDto getWeatherDataForOffer(WeatherOffer request) throws JsonProcessingException {
        WeatherResponseDto weatherData = weatherClientService.getWeatherData(request.getCity());
        if (request.getWeatherType() != null && request.getWeatherType().equals(weatherData.getWeatherType().toLowerCase()) && !request.isStatus()){
            if (weatherData.getTemperature() >= request.getMinTemperature() && weatherData.getTemperature() <= request.getMaxTemperature()){
                request.setStatus(true);
                weatherOfferRepository.save(request);
                return weatherData;
            }
        }
        if (request.getWeatherType() == null && !request.isStatus()){
            if (weatherData.getTemperature() >= request.getMinTemperature() && weatherData.getTemperature() <= request.getMaxTemperature()){
                request.setStatus(true);
                weatherOfferRepository.save(request);
                return weatherData;
            }
        }
        return null;
    }

    @Override
    public List<WeatherOffer> getAllWeatherOffer() {
        return weatherOfferRepository.findAll();
    }

    @Override
    public ResponseDto saveWeatherOffer(WeatherOfferRequestDto request) {
        checkRequest(request);

        WeatherOffer weatherOffer = WeatherOffer.builder()
                .weatherType(request.getWeatherType().toLowerCase())
                .city(request.getCity())
                .maxTemperature(request.getMaxTemperature())
                .minTemperature(request.getMinTemperature())
                .email(request.getEmail())
                .build();
        weatherOfferRepository.save(weatherOffer);

        return new ResponseDto("Offer accepted!");
    }

    private void checkRequest(WeatherOfferRequestDto request) {
        WeatherOffer weatherOfferByUsername = weatherOfferRepository.findWeatherOfferByEmail(request.getEmail());

        if (weatherOfferByUsername != null && !weatherOfferByUsername.isStatus()){
            throw new RuntimeException("You have a pending request. Please wait for a response from him.");
        }
    }


}
