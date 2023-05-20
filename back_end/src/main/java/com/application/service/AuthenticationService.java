package com.application.service;

import com.application.dto.UserAuthenticationDTO;
import com.application.exception.AuthenticationException;

public interface AuthenticationService {

    void authentication(UserAuthenticationDTO userDTO) throws AuthenticationException;
}
