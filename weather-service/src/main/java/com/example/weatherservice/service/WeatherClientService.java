package com.example.weatherservice.service;

import com.example.weatherservice.model.dto.response.WeatherResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class WeatherClientService {
    private final ObjectMapper objectMapper;
    RestTemplate restTemplate = new RestTemplate();
    String apiKey = "d6cc45be1273e2da7a015d1bb0cff844";

    public WeatherResponseDto getWeatherData(String city) throws JsonProcessingException {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
        String jsonData = restTemplate.getForObject(url, String.class);

        return generateWeatherResponseDto(jsonData);
    }

    private WeatherResponseDto generateWeatherResponseDto(String jsonData) throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(jsonData);
        JsonNode cord = root.path("coord");
        JsonNode weather = root.path("weather").get(0);
        JsonNode main = root.path("main");
        JsonNode wind = root.path("wind");
        JsonNode sys = root.path("sys");

        int timezoneOffset = root.path("timezone").asInt();
        ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.ofTotalSeconds(timezoneOffset));
        LocalDateTime sunriseTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(sys.path("sunrise").asLong() * 1000), zoneId);
        LocalDateTime sunsetTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(sys.path("sunset").asLong() * 1000), zoneId);

        return WeatherResponseDto.builder()
                .city(root.path("name").asText())
                .weatherType(weather.path("main").asText())
                .weatherTypeDescription(weather.path("description").asText())
                .cordLength(cord.path("lon").asDouble())
                .cordWidth(cord.path("lat").asDouble())
                .temperature(formatDouble(main.path("temp").asDouble() - 273.15))
                .feelsTemp(formatDouble(main.path("feels_like").asDouble() - 273.15))
                .minTemp(formatDouble(main.path("temp_min").asDouble() - 273.15))
                .maxTemp(formatDouble(main.path("temp_max").asDouble() - 273.15))
                .pressure(main.path("pressure").asInt())
                .humidity(main.path("humidity").asInt())
                .windSpeed(wind.path("speed").asDouble())
                .visibility(classifyVisibility(root.path("visibility").asInt()))
                .country(sys.path("country").asText())
                .sunrise(formatDate(sunriseTime))
                .sunset(formatDate(sunsetTime))
                .build();
    }

    private double formatDouble(double temp) {
        String format = String.format("%.2f", temp);

        return Double.parseDouble(format);
    }

    private String formatDate(LocalDateTime time) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy | HH:mm:ss");
        return dateTimeFormatter.format(time);
    }

    public String classifyVisibility(int visibility) {
        if (visibility < 1000) {
            return "Very Low";
        } else if (visibility < 5000) {
            return "Low";
        } else if (visibility < 10000) {
            return "Moderate";
        } else {
            return "High";
        }

    }
}
