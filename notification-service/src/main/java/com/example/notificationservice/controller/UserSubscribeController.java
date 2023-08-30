package com.example.notificationservice.controller;

import com.example.notificationservice.model.dto.request.SubscribeRequestDto;
import com.example.notificationservice.service.impl.SubscribeUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSubscribeController {
    private final SubscribeUserService subscribeUserService;

    @PostMapping("/subscription")
    public ResponseEntity<String> subscription(@RequestBody SubscribeRequestDto subscribeRequestDto){
        subscribeUserService.checkUser(subscribeRequestDto);
        return ResponseEntity.ok().body("Your transaction has been successfully received!");
    }

}
