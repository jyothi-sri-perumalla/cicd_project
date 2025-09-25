package com.example.demo.dto;

import java.time.LocalDateTime;

public class LocationVisitResponse {
    private Long id;
    private Long officerId;
    private String location;
    private LocalDateTime visitDate;
    private String formType;
    private String formData;
    private String status;
    private LocalDateTime submittedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public LocationVisitResponse() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public LocalDateTime getVisitDate() {
        return visitDate;
    }
    
    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
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
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
        return "LocationVisitResponse{" +
                "id=" + id +
                ", officerId=" + officerId +
                ", location='" + location + '\'' +
                ", formType='" + formType + '\'' +
                ", status='" + status + '\'' +
                ", visitDate=" + visitDate +
                '}';
    }
}
