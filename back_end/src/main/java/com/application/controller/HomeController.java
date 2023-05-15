package com.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class HomeController {



    @GetMapping
    public ResponseEntity<String> homePage(){
        log.info("Get home page. ");
        return ResponseEntity.ok("home");
    }

}
