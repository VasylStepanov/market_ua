package com.application.service;

import com.application.dto.UserDTO;
import com.application.exception.UserNotFoundException;

public interface UserService {

    UserDTO getUserByLogin(String login) throws UserNotFoundException;
}
