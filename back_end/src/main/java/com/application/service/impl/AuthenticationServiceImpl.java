package com.application.service.impl;

import com.application.dto.UserAuthenticationDTO;
import com.application.exception.AuthenticationException;
import com.application.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    @Override
    public void authentication(UserAuthenticationDTO userDTO) throws AuthenticationException {

    }
}
