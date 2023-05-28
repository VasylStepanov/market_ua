package com.application.service.impl;

import com.application.dto.UserRegistrationDTO;
import com.application.entity.Role;
import com.application.entity.User;
import com.application.entity.UserProfile;
import com.application.exception.RegistrationException;
import com.application.repository.UserProfileRepository;
import com.application.repository.UserRepository;
import com.application.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    @Transactional
    public void register(UserRegistrationDTO userDTO) throws RegistrationException {
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

        try {
            userRepository.createUser(user);
            userProfileRepository.createUserProfile(userProfile);
        } catch (Exception e){
            throw new RegistrationException("Sorry, but user with this email already exists.");
        }

        log.info(String.format("user %s successfully signed up.", userProfile.getEmail()));
    }
}
