package com.example.demo.service;

import com.example.demo.model.PasswordQualityResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordQualityService {

    public PasswordQualityResponse evaluatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return new PasswordQualityResponse(
                password, 
                0, 
                "Very Weak", 
                "Password cannot be empty"
            );
        }

        int score = 0;
        List<String> feedback = new ArrayList<>();

        int length = password.length();
        if (length >= 16) {
            score += 30;
        } else if (length >= 12) {
            score += 25;
        } else if (length >= 8) {
            score += 15;
            feedback.add("Consider making password longer (12+ characters)");
        } else {
            score += 5;
            feedback.add("Password is too short (minimum 8 characters recommended)");
        }

        if (password.matches(".*[A-Z].*")) {
            score += 20;
        } else {
            feedback.add("Add uppercase letters");
        }

        if (password.matches(".*[a-z].*")) {
            score += 20;
        } else {
            feedback.add("Add lowercase letters");
        }

        if (password.matches(".*\\d.*")) {
            score += 15;
        } else {
            feedback.add("Add numbers");
        }

        if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            score += 15;
        } else {
            feedback.add("Add special characters (!@#$%^&*, etc.)");
        }

        if (password.matches("^[0-9]+$")) {
            score -= 20;
            feedback.add("Avoid using only numbers");
        }
        if (password.toLowerCase().matches(".*(password|123456|qwerty|admin).*")) {
            score -= 30;
            feedback.add("Avoid common password patterns");
        }

        score = Math.max(0, Math.min(100, score));

        String quality;
        if (score >= 80) {
            quality = "Strong";
            feedback.add("Great password!");
        } else if (score >= 60) {
            quality = "Good";
        } else if (score >= 40) {
            quality = "Fair";
        } else if (score >= 20) {
            quality = "Weak";
        } else {
            quality = "Very Weak";
        }

        String feedbackMessage = feedback.isEmpty() ? "Password meets all criteria" : String.join(". ", feedback);

        return new PasswordQualityResponse(password, score, quality, feedbackMessage);
    }
}