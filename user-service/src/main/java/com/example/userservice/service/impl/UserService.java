package com.example.userservice.service.impl;

import com.example.userservice.exception.ApplicationException;
import com.example.userservice.model.dto.request.SubscribeRequestDto;
import com.example.userservice.model.entity.User;
import com.example.userservice.model.enums.Exceptions;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final AmqpTemplate rabbitTemplate;
    private final DirectExchange exchange;


    @Override
    @RabbitListener(queues = "firstStepQueue")
    public void checkUserBalance(SubscribeRequestDto subscribeRequestDto) {
        Optional<User> optionalUser = userRepository.findUserByUsernameOrEmail(subscribeRequestDto.getEmail());
        optionalUser.ifPresentOrElse(user -> {
            if (user.getWallet() >= 100){
                user.setWallet(user.getWallet() - 100);
                userRepository.save(user);
                rabbitTemplate.convertAndSend(exchange.getName(),"secondRoute",subscribeRequestDto);
            }else {
                System.out.println("Insufficient funds -> accountId: " + user.getId() + " balance: " + user.getWallet() + " amount: 100$");
            }
        },
                () -> System.out.println("Account not found!"));

    }
}
