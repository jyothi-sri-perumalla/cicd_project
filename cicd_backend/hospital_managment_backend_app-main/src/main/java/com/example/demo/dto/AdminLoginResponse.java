package com.example.demo.dto;

import java.time.LocalDateTime;

public class AdminLoginResponse {

  private String token;
  private String tokenType = "Bearer";
  private Long adminId;
  private String email;
  private String fullName;
  private LocalDateTime lastLogin;
  private String message;

  // Default constructor
  public AdminLoginResponse() {
  }

  // Constructor for successful login
  public AdminLoginResponse(String token, Long adminId, String email, String fullName, LocalDateTime lastLogin) {
    this.token = token;
    this.adminId = adminId;
    this.email = email;
    this.fullName = fullName;
    this.lastLogin = lastLogin;
    this.message = "Login successful";
  }

  // Constructor for error response
  public AdminLoginResponse(String message) {
    this.message = message;
  }

  // Getters and Setters
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public Long getAdminId() {
    return adminId;
  }

  public void setAdminId(Long adminId) {
    this.adminId = adminId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
