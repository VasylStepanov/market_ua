package com.application.controller;

import com.application.dto.UserRegistrationDTO;
import com.application.exception.RegistrationException;
import com.application.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService service;

    @PostMapping("/authentication")
    public ResponseEntity<String> authentication(){
        log.info("Post authentication data");
        return ResponseEntity.ok("Welcome");
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserRegistrationDTO userDTO) throws RegistrationException {
        log.info("Post registration data");
        service.register(userDTO);
        return ResponseEntity.ok("Signed up successfully!");
    }
}
