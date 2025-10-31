package com.example.demo.service;

import com.example.demo.model.EmailValidationResponse;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EmailValidationService {

    private static final String EMAIL_REGEX = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public EmailValidationResponse validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return new EmailValidationResponse(
                email, 
                false, 
                "Email address cannot be empty"
            );
        }

        email = email.trim();

        if (email.length() > 254) {
            return new EmailValidationResponse(
                email, 
                false, 
                "Email address is too long (max 254 characters)"
            );
        }

        if (!email.contains("@")) {
            return new EmailValidationResponse(
                email, 
                false, 
                "Email address must contain @ symbol"
            );
        }

        long atCount = email.chars().filter(ch -> ch == '@').count();
        if (atCount > 1) {
            return new EmailValidationResponse(
                email, 
                false, 
                "Email address must contain exactly one @ symbol"
            );
        }

        String[] parts = email.split("@");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            return new EmailValidationResponse(
                email, 
                false, 
                "Email address must have both local and domain parts"
            );
        }

        if (!parts[1].contains(".")) {
            return new EmailValidationResponse(
                email, 
                false, 
                "Domain must contain at least one dot"
            );
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return new EmailValidationResponse(
                email, 
                false, 
                "Email address format is invalid"
            );
        }

        return new EmailValidationResponse(
            email, 
            true, 
            "Email address is valid"
        );
    }
}