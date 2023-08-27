package com.example.weatherservice.repository;

import com.example.weatherservice.model.entity.WeatherOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherOfferRepository extends JpaRepository<WeatherOffer,Long> {
    WeatherOffer findWeatherOfferByEmail(String email);

}

