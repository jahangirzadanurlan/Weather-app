package com.example.weatherservice.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherOfferRequestDto {
    String email;
    String city;
    String weatherType;
    double minTemperature;
    double maxTemperature;
}
