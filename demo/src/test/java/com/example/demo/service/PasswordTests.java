package com.example.demo.service;

import com.example.demo.model.PasswordQualityResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordQualityServiceTest {

    private PasswordQualityService passwordQualityService;

    @BeforeEach
    void setUp() {
        passwordQualityService = new PasswordQualityService();
    }

    @Test
    void testEmptyPassword() {
        PasswordQualityResponse response = passwordQualityService.evaluatePassword("");
        assertEquals(0, response.getScore());
        assertEquals("Very Weak", response.getQuality());
        assertTrue(response.getFeedback().contains("empty"));
    }

    @Test
    void testWeakPassword() {
        PasswordQualityResponse response = passwordQualityService.evaluatePassword("abc");
        assertTrue(response.getScore() < 40);
        assertTrue(response.getQuality().equals("Weak") || response.getQuality().equals("Very Weak"));
    }

    @Test
    void testCommonPassword() {
        PasswordQualityResponse response = passwordQualityService.evaluatePassword("password123");
        assertTrue(response.getFeedback().toLowerCase().contains("common"));
    }

    @Test
    void testGoodPassword() {
        PasswordQualityResponse response = passwordQualityService.evaluatePassword("MyPass123");
        assertTrue(response.getScore() >= 40);
    }

    @Test
    void testStrongPassword() {
        PasswordQualityResponse response = passwordQualityService.evaluatePassword("MyP@ssw0rd!2024");
        assertTrue(response.getScore() >= 70);
        assertTrue(response.getQuality().equals("Good") || response.getQuality().equals("Strong"));
    }

    @Test
    void testPasswordLength() {
        PasswordQualityResponse short1 = passwordQualityService.evaluatePassword("Ab1!");
        PasswordQualityResponse long1 = passwordQualityService.evaluatePassword("Abcd1234!@#$Extra");
        assertTrue(long1.getScore() > short1.getScore());
    }
}