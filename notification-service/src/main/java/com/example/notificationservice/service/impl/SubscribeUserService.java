package com.example.notificationservice.service.impl;

import com.example.notificationservice.model.dto.request.SubscribeRequestDto;
import com.example.notificationservice.model.entity.DailySubscribeUser;
import com.example.notificationservice.repository.DailySubscribeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeUserService {
    private  final DailySubscribeUserRepository dailySubscribeUserRepository;

    public String saveUser(SubscribeRequestDto request){
        DailySubscribeUser user = DailySubscribeUser.builder()
                .city(request.getCity())
                .email(request.getEmail())
                .build();

        dailySubscribeUserRepository.save(user);
        return "Save is successfully";
    }
}
