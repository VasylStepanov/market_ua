package com.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/profile")
public class UserController {

    @GetMapping
    public ResponseEntity<String> getProfile(){
        log.info("Get profile page");
        return ResponseEntity.ok("Profile page");
    }
}

