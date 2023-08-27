package com.example.notificationservice.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherOfferRequestDto {
    String username;
    String city;
    String weatherType;
    double minTemperature;
    double maxTemperature;
}
