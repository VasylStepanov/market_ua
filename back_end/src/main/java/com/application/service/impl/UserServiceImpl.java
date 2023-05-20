package com.application.service.impl;

import com.application.dto.UserDTO;
import com.application.exception.UserNotFoundException;
import com.application.repository.UserRepository;;
import com.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDTO getUserByLogin(String login) throws UserNotFoundException {
        return repository.findUserByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException("No user with this login."));
    }
}
