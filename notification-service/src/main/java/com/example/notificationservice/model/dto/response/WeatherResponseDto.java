package com.example.notificationservice.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherResponseDto {
    double cordLength;
    double cordWidth;

    String weatherType;
    String weatherTypeDescription;

    double temperature;
    double feelsTemp;
    double minTemp;
    double maxTemp;
    int pressure;
    int humidity;

    double windSpeed;
    String visibility;

    String country;
    String sunrise;
    String sunset;
    String city;

}
