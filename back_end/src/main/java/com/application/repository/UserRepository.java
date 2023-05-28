package com.application.repository;

import com.application.dto.UserAuthenticationDTO;
import com.application.dto.UserDTO;
import com.application.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findUserById(UUID id);
    Optional<User> findUserByLogin(String login);

    Optional<UserDTO> findUserDTOByLogin(String login);
    Optional<UserDTO> findUserDTOByEmail(String email);
    Optional<UserDTO> findUserDTOByPhoneNumber(String phoneNumber);

    Optional<UserAuthenticationDTO> findAuthUserByEmail(String email);
    Optional<UserAuthenticationDTO> findAuthUserByPhoneNumber(String phoneNumber);

    void createUser(User user);
}
