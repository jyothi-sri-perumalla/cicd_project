package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdminRegistrationRequest {

  @Email(message = "Email should be valid")
  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 6, message = "Password must be at least 6 characters")
  private String password;

  @NotBlank(message = "Full name is required")
  private String fullName;

  // Default constructor
  public AdminRegistrationRequest() {
  }

  // Constructor
  public AdminRegistrationRequest(String email, String password, String fullName) {
    this.email = email;
    this.password = password;
    this.fullName = fullName;
  }

  // Getters and Setters
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override
  public String toString() {
    return "AdminRegistrationRequest{" +
        "email='" + email + '\'' +
        ", fullName='" + fullName + '\'' +
        '}';
  }
}
