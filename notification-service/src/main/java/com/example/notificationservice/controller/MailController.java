package com.example.notificationservice.controller;

import com.example.notificationservice.model.dto.request.UserRegisterEmailRequestDto;
import com.example.notificationservice.service.impl.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {
    private final MailSenderService mailSenderService;

    @PostMapping("/confirm-user")
    public void sendConfirmationMail(@RequestBody UserRegisterEmailRequestDto request){
        mailSenderService.sendMail(request);
    }


}
