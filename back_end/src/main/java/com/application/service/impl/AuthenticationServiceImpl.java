package com.application.service.impl;

import com.application.checking.UserDataChecker;
import com.application.dto.UserRegistrationDTO;
import com.application.entity.Role;
import com.application.entity.User;
import com.application.entity.UserProfile;
import com.application.exception.RegistrationException;
import com.application.repository.UserProfileRepository;
import com.application.repository.UserRepository;
import com.application.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private final UserDataChecker checker;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserProfileRepository userProfileRepository;

    @Override
    @Transactional
    public void register(UserRegistrationDTO userDTO) throws RegistrationException {

        checker.registrationDataCheck(userDTO);

        User user = User.builder()
                .id(UUID.randomUUID())
                .login(userDTO.getLogin())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .enabled(true)
                .role(Role.USER)
                .build();

        UserProfile userProfile = UserProfile.builder()
                .id(UUID.randomUUID())
                .email(userDTO.getEmail())
                .user(user)
                .build();

        userRepository.save(user);
        userProfileRepository.save(userProfile);
        log.info(String.format("user %s successfully signed up.", userProfile.getEmail()));
    }
}
