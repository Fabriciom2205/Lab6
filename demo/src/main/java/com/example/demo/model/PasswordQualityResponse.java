package com.example.demo.model;

public class PasswordQualityResponse {
    private String password;
    private int score;
    private String quality;
    private String feedback;

    public PasswordQualityResponse() {}
    
    public PasswordQualityResponse(String password, int score, String quality, String feedback) {
        this.password = password;
        this.score = score;
        this.quality = quality;
        this.feedback = feedback;
    }

    // Getters and setters
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public String getQuality() { return quality; }
    public void setQuality(String quality) { this.quality = quality; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
