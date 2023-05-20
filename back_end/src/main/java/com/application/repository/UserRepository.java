package com.application.repository;

import com.application.dto.UserDTO;
import com.application.entity.User;

import java.util.Optional;

public interface UserRepository {


    Optional<UserDTO> findUserByLogin(String login);

    void createUser(User user);
}
