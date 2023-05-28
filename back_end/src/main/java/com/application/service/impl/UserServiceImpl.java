package com.application.service.impl;

import com.application.dto.UserDTO;
import com.application.entity.User;
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
    public User getUserByLogin(String login) {
        return repository.findUserByLogin(login).get();
    }

    @Override
    public UserDTO getUserDTOByLogin(String login) throws UserNotFoundException {
        return repository.findUserDTOByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException("No user with this login."));
    }

    @Override
    public UserDTO getUserDTOByEmail(String email) throws UserNotFoundException {
        return repository.findUserDTOByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("No user with this email."));
    }

    @Override
    public UserDTO getUserDTOByPhoneNumber(String phoneNumber) throws UserNotFoundException {
        return repository.findUserDTOByPhoneNumber(phoneNumber).orElseThrow(
                () -> new UsernameNotFoundException("No user with this phone number."));
    }
}
