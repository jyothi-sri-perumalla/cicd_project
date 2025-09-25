package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OfficerResponse {
    private Long id;
    private String fullName;
    private String email;
    private String mobileNumber;
    private List<String> locations;
    private LocalDateTime createdAt;
    private Boolean isActive;
    
    // Constructors
    public OfficerResponse() {}
    
    public OfficerResponse(Long id, String fullName, String email, String mobileNumber, 
                          List<String> locations, LocalDateTime createdAt, Boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.locations = locations;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public List<String> getLocations() {
        return locations;
    }
    
    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
