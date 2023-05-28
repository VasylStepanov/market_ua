package com.application.service;

import com.application.dto.UserDTO;
import com.application.entity.User;
import com.application.exception.UserNotFoundException;

public interface UserService {

    User getUserByLogin(String login);

    UserDTO getUserDTOByLogin(String login) throws UserNotFoundException;
    UserDTO getUserDTOByEmail(String email) throws UserNotFoundException;
    UserDTO getUserDTOByPhoneNumber(String phoneNumber) throws UserNotFoundException;
}
