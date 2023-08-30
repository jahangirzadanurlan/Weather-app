package com.example.notificationservice.service.impl;

import com.example.notificationservice.model.dto.request.SubscribeRequestDto;
import com.example.notificationservice.model.entity.DailySubscribeUser;
import com.example.notificationservice.repository.DailySubscribeUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeUserService {
    private  final DailySubscribeUserRepository dailySubscribeUserRepository;
    private final AmqpTemplate rabbitTemplate;
    private final DirectExchange exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;


    public void checkUser(SubscribeRequestDto request){
        rabbitTemplate.convertAndSend(exchange.getName(),routingKey,request);
    }

    @RabbitListener(queues = "secondStepQueue")
    public void saveDailySubscribeUser(SubscribeRequestDto subscribeRequestDto){
        DailySubscribeUser dailyUser = DailySubscribeUser.builder()
                .city(subscribeRequestDto.getCity())
                .email(subscribeRequestDto.getEmail())
                .build();
        dailySubscribeUserRepository.save(dailyUser);
        rabbitTemplate.convertAndSend(exchange.getName(),"thirdRoute",subscribeRequestDto);
    }
}
