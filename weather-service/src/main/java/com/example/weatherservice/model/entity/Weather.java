package com.example.weatherservice.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Weather {
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
    int visibility;

    String country;
    long sunrise;
    long sunset;
    String city;

}
