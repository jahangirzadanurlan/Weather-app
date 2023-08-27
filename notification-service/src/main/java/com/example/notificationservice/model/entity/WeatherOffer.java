package com.example.notificationservice.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherOffer {
    Long id;
    String email;
    String city;
    String weatherType;
    double minTemperature;
    double maxTemperature;
    boolean status;
}
