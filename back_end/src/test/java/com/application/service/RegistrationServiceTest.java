package com.application.service;

import com.application.dto.UserRegistrationDTO;
import com.application.exception.UserNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationServiceTest {

    @Autowired
    private RegistrationService registrationService;

    @Order(1)
    @Test
    public void registrationSuccessTest() {
        UserRegistrationDTO userRegistrationDTO = UserRegistrationDTO.builder()
                .login("testLogin")
                .email("test@gmail.com")
                .password("encoded123_Password")
                .build();

        Assertions.assertDoesNotThrow(() -> registrationService.register(userRegistrationDTO));
    }
}
