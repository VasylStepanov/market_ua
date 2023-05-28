package com.application.controller;

import com.application.dto.AuthResponseDTO;
import com.application.dto.AuthRequestDTO;
import com.application.exception.AuthenticationException;
import com.application.exception.UnauthorizedException;
import com.application.exception.UserNotFoundException;
import com.application.security.jwt.TokenDetails;
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
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> authentication(@RequestBody AuthRequestDTO userDto) throws AuthenticationException, UserNotFoundException, UnauthorizedException {
        log.info("Post authentication data");
        AuthResponseDTO responseDTO = service.authentication(userDto);

        return ResponseEntity.ok(responseDTO);
    }

}
