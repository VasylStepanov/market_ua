package com.application.checking;

import com.application.dto.UserRegistrationDTO;
import com.application.exception.RegistrationException;
import org.springframework.stereotype.Component;

@Component
public class UserDataChecker {

    private static final String LOGIN = "^[A-Za-z0-9]{3,32}$";
    private static final String PHONE_NUMBER = "^\\+?3?8?(0\\d{9})$";
    private static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–_[{}]:;',?/*~$^+=<>]).{8,20}$";

    public boolean isValidLogin(String login){
        return login.matches(LOGIN);
    }

    public boolean isValidPhoneNumber(String phoneNumber){
        return phoneNumber.matches(PHONE_NUMBER);
    }

    public boolean isValidEmail(String email){
        return email.matches(EMAIL);
    }

    public boolean isValidPassword(String password){
        return password.matches(PASSWORD);
    }

    public void registrationDataCheck(UserRegistrationDTO user) throws RegistrationException{
        if(!isValidLogin(user.getLogin()))
            throw new RegistrationException("Login is invalid");
        if(!isValidEmail(user.getEmail()))
            throw new RegistrationException("Email is invalid");
        if(!isValidPassword(user.getPassword()))
            throw new RegistrationException("Password is invalid, should has at least a number, big letter and symbol");
    }
}
