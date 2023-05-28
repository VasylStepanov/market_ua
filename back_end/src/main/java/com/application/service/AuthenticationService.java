package com.application.service;

import com.application.dto.AuthRequestDTO;
import com.application.dto.AuthResponseDTO;
import com.application.dto.UserAuthenticationDTO;
import com.application.exception.AuthenticationException;
import com.application.exception.UnauthorizedException;
import com.application.exception.UserNotFoundException;
import com.application.security.jwt.TokenDetails;

public interface AuthenticationService {

    AuthResponseDTO authentication(AuthRequestDTO userDTO) throws AuthenticationException, UserNotFoundException, UnauthorizedException;

    UserAuthenticationDTO getAuthUserByEmail(String email) throws AuthenticationException;

    UserAuthenticationDTO getAuthUserByPhoneNumber(String phoneNumber) throws AuthenticationException;
}
