package com.application.controller;

import com.application.dto.UserRegistrationDTO;
import com.application.exception.RegistrationException;
import com.application.service.RegistrationService;
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
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    private final RegistrationService service;

    @PostMapping
    public ResponseEntity<String> registration(@RequestBody UserRegistrationDTO userDTO) throws RegistrationException {
        log.info("Post registration data");
        service.register(userDTO);
        return ResponseEntity.ok("Signed up successfully!");
    }
}
