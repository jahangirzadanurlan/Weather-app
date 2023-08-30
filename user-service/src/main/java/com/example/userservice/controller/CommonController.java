package com.example.userservice.controller;

import com.example.userservice.service.WeatherServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth2")
public class CommonController {

//    @GetMapping("/success")
//    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
//        if (principal == null) {
//            System.out.println("Principal is null");
//            return "error";
//        }
//        model.addAttribute("username", principal.getAttribute("login"));
//        return "home";
//    }

    @GetMapping("/success")
    public String index() {
        return "home";
    }
}
