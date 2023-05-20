package com.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> exception(RegistrationException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> exception(PSQLException exception){
        if(exception.getMessage().contains("email_unique"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Sorry, but user with this email already exists.");
        else if(exception.getMessage().contains("login_unique"))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Sorry, but user with this login already exists.");
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: Please wait, our workers are working on this problem!");
    }

}
