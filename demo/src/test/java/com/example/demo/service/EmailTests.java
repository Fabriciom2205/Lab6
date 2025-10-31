package com.example.demo.service;

import com.example.demo.model.EmailValidationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidationServiceTest {

    private EmailValidationService emailValidationService;

    @BeforeEach
    void setUp() {
        emailValidationService = new EmailValidationService();
    }

    @Test
    void testValidEmail() {
        EmailValidationResponse response = emailValidationService.validateEmail("test@example.com");
        assertTrue(response.isValid());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void testEmptyEmail() {
        EmailValidationResponse response = emailValidationService.validateEmail("");
        assertFalse(response.isValid());
        assertTrue(response.getMessage().contains("empty"));
    }

    @Test
    void testEmailWithoutAtSymbol() {
        EmailValidationResponse response = emailValidationService.validateEmail("userexample.com");
        assertFalse(response.isValid());
        assertTrue(response.getMessage().toLowerCase().contains("@"));
    }

    @Test
    void testEmailWithoutDomain() {
        EmailValidationResponse response = emailValidationService.validateEmail("user@");
        assertFalse(response.isValid());
    }

    @Test
    void testEmailWithoutDomainExtension() {
        EmailValidationResponse response = emailValidationService.validateEmail("user@example");
        assertFalse(response.isValid());
        assertTrue(response.getMessage().toLowerCase().contains("dot"));
    }

    @Test
    void testValidEmailWithSubdomain() {
        EmailValidationResponse response = emailValidationService.validateEmail("user@mail.example.com");
        assertTrue(response.isValid());
    }
}