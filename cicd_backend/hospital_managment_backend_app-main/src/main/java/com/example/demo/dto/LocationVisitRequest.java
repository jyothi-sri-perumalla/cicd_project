package com.example.demo.dto;

import java.time.LocalDateTime;

public class LocationVisitRequest {
    private Long officerId;
    private String location;
    private String formType;
    private String formData;
    
    // Constructors
    public LocationVisitRequest() {}
    
    public LocationVisitRequest(Long officerId, String location, String formType, String formData) {
        this.officerId = officerId;
        this.location = location;
        this.formType = formType;
        this.formData = formData;
    }
    
    // Getters and Setters
    public Long getOfficerId() {
        return officerId;
    }
    
    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getFormType() {
        return formType;
    }
    
    public void setFormType(String formType) {
        this.formType = formType;
    }
    
    public String getFormData() {
        return formData;
    }
    
    public void setFormData(String formData) {
        this.formData = formData;
    }
    
    @Override
    public String toString() {
        return "LocationVisitRequest{" +
                "officerId=" + officerId +
                ", location='" + location + '\'' +
                ", formType='" + formType + '\'' +
                ", formData='" + formData + '\'' +
                '}';
    }
}
