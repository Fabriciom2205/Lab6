package com.example.demo.model;

public class EmailValidationResponse {
    private String email;
    private boolean valid;
    private String message;

    public EmailValidationResponse() {}
    
    public EmailValidationResponse(String email, boolean valid, String message) {
        this.email = email;
        this.valid = valid;
        this.message = message;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isValid() { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
