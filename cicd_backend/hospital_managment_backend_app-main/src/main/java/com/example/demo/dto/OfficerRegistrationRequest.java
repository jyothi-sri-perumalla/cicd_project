package com.example.demo.dto;

import java.util.List;

public class OfficerRegistrationRequest {
    private String fullName;
    private String email;
    private String mobileNumber;
    private List<String> locations;
    
    // Constructors
    public OfficerRegistrationRequest() {}
    
    public OfficerRegistrationRequest(String fullName, String email, String mobileNumber, List<String> locations) {
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.locations = locations;
    }
    
    // Getters and Setters
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
    
    @Override
    public String toString() {
        return "OfficerRegistrationRequest{" +
            "fullName='" + fullName + '\'' +
            ", email='" + email + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            ", locations=" + locations +
            '}';
    }
}
