package com.application.service;

import com.application.dto.UserRegistrationDTO;
import com.application.exception.RegistrationException;

public interface AuthenticationService {

    void register(UserRegistrationDTO userDTO) throws RegistrationException;
}
