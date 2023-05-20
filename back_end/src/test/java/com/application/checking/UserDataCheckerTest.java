package com.application.checking;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDataCheckerTest {

    private UserDataChecker userDataChecker = new UserDataChecker();


    @Order(1)
    @Test
    void testIsValidLoginTrue(){
        String validLogin = "test12";
        assertTrue(userDataChecker.isValidLogin(validLogin));
    }

    @Order(2)
    @Test
    void testIsValidPhoneNumberTrue(){
        String validPhoneNumber = "+380123456789";
        assertTrue(userDataChecker.isValidPhoneNumber(validPhoneNumber));
    }

    @Order(3)
    @Test
    void testIsValidEmailTrue(){
        String validEmail = "test@example.com";
        assertTrue(userDataChecker.isValidEmail(validEmail));
    }


    @Order(4)
    @Test
    void testIsValidPasswordTrue(){
        String validPassword = "Password_123";
        assertTrue(userDataChecker.isValidPassword(validPassword));
    }

    @Order(5)
    @Test
    void testIsValidLoginFalse(){
        String validLogin = "test1&#";
        assertFalse(userDataChecker.isValidLogin(validLogin));
    }

    @Order(6)
    @Test
    void testIsValidPhoneNumberFalse(){
        String[] validPhoneNumbers = {
                "+3801234567891",
                "+30 12 345 6789",
                "+180-12-345-6789",
                "-380 (12)-345-6789"
        };

        for(String validPhoneNumber : validPhoneNumbers)
            assertFalse(userDataChecker.isValidPhoneNumber(validPhoneNumber));
    }

    @Order(7)
    @Test
    void testIsValidEmailFalse(){
        String validEmail = "testexample.com";
        assertFalse(userDataChecker.isValidEmail(validEmail));
    }


    @Order(8)
    @Test
    void testIsValidPasswordFalse(){
        String validPassword = "password";
        assertFalse(userDataChecker.isValidPassword(validPassword));
    }
}
