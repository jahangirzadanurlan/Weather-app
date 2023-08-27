package com.example.weatherservice.model.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherOfferResponseDto {
    String username;
    String city;
    String weatherType;
    double minTemperature;
    double maxTemperature;
}
