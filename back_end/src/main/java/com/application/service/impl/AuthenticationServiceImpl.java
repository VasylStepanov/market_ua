package com.application.service.impl;

import com.application.dto.AuthRequestDTO;
import com.application.dto.AuthResponseDTO;
import com.application.dto.UserAuthenticationDTO;
import com.application.exception.AuthenticationException;
import com.application.exception.UnauthorizedException;
import com.application.exception.UserNotFoundException;
import com.application.repository.jdbc.JdbcUserRepository;
import com.application.security.jwt.TokenDetails;
import com.application.security.jwt.JwtSecurityService;
import com.application.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtSecurityService jwtSecurityService;
    
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JdbcUserRepository repository;

    @Autowired
    private AuthenticationManager manager;


    @Override
    public AuthResponseDTO authentication(AuthRequestDTO authRequestDTO) throws AuthenticationException{
        log.info("AuthenticationServiceImpl create token");
        UserAuthenticationDTO userDTO;

        boolean userDataType = authRequestDTO.getEmail() != null;
        if(userDataType)
            userDTO = getAuthUserByEmail(authRequestDTO.getEmail());
        else if (authRequestDTO.getPhoneNumber() != null)
            userDTO = getAuthUserByPhoneNumber(authRequestDTO.getPhoneNumber());
        else
            throw new AuthenticationException("No email or phone number were sent!");

        manager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getLogin(), authRequestDTO.getPassword()));

        TokenDetails jwt = jwtSecurityService.generateToken(userDTO);

        return AuthResponseDTO.builder()
                .token(jwt.getToken()).build();
    }

    @Override
    public UserAuthenticationDTO getAuthUserByEmail(String email) throws AuthenticationException {
        return repository.findAuthUserByEmail(email).orElseThrow(() -> new AuthenticationException("Email or password is wrong!"));
    }

    @Override
    public UserAuthenticationDTO getAuthUserByPhoneNumber(String phoneNumber) throws AuthenticationException {
        return repository.findAuthUserByPhoneNumber(phoneNumber).orElseThrow(() -> new AuthenticationException("Phone number or password is wrong!"));
    }
}
