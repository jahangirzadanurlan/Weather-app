package com.example.userservice.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public String hello(){
        log.info("Hello user lets go school");
        return "Hello i am user!";
    }

    @PostMapping//
    public int getBalance(@RequestBody String email){
        return 0;
    }

}

