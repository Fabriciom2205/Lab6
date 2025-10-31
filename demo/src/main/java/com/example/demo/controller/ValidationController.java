package com.example.demo.controller;

import com.example.demo.model.EmailValidationRequest;
import com.example.demo.model.EmailValidationResponse;
import com.example.demo.model.PasswordQualityRequest;
import com.example.demo.model.PasswordQualityResponse;
import com.example.demo.service.EmailValidationService;
import com.example.demo.service.PasswordQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ValidationController {

    @Autowired
    private PasswordQualityService passwordQualityService;

    @Autowired
    private EmailValidationService emailValidationService;

    @PostMapping("/password-quality")
    public ResponseEntity<PasswordQualityResponse> checkPasswordQuality(
            @RequestBody PasswordQualityRequest request) {
        PasswordQualityResponse response = passwordQualityService.evaluatePassword(request.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/email-address-valid")
    public ResponseEntity<EmailValidationResponse> validateEmailAddress(
            @RequestBody EmailValidationRequest request) {
        EmailValidationResponse response = emailValidationService.validateEmail(request.getEmail());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Lab 6 REST API is running!");
    }
}