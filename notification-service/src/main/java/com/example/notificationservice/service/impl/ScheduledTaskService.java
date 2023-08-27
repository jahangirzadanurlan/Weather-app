package com.example.notificationservice.service.impl;

import com.example.notificationservice.model.dto.response.WeatherResponseDto;
import com.example.notificationservice.model.entity.DailySubscribeUser;
import com.example.notificationservice.model.entity.WeatherOffer;
import com.example.notificationservice.repository.DailySubscribeUserRepository;
import com.example.notificationservice.service.WeatherClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledTaskService {
    private final WeatherClientService weatherClientService;
    private final DailySubscribeUserRepository dailySubscribeUserRepository;
    private final MailSenderService mailSenderService;

    @Scheduled(fixedRate = 40 * 60 *1000) //Every 40 minutes
    public void checkWeatherOffer(){
        List<WeatherOffer> allWeatherOffer = weatherClientService.getAllWeatherOffer();
        for (WeatherOffer weatherOffer : allWeatherOffer){
            WeatherResponseDto weatherDataForOffer = weatherClientService.getWeatherDataForOffer(weatherOffer);
            if (weatherDataForOffer != null){
                mailSenderService.sendWeatherMail(weatherOffer.getEmail(),weatherDataForOffer);
            }
        }
    }

    @Scheduled(cron = "0 0 12 * * ?")  //12 AM
    public void sendDailyWeatherMessage(){
        List<DailySubscribeUser> allUser = dailySubscribeUserRepository.findAll();
        for (DailySubscribeUser user : allUser){
            WeatherResponseDto weatherData = weatherClientService.getWeatherData(user.getCity());
            mailSenderService.sendWeatherMail(user.getEmail(),weatherData);
        }
    }



}
