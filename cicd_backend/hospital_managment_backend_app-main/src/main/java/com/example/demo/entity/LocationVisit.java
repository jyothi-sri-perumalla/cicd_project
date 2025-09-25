package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "location_visits")
public class LocationVisit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "officer_id", nullable = false)
    private Long officerId;
    
    @Column(name = "location", nullable = false)
    private String location;
    
    @Column(name = "visit_date", nullable = false)
    private LocalDateTime visitDate;
    
    @Column(name = "form_type", nullable = false)
    private String formType; // "ESI_MED6", "ESI_MED6A", "KPI_REPORT"
    
    @Column(name = "form_data", columnDefinition = "TEXT")
    private String formData; // JSON string of form data
    
    @Column(name = "status")
    private String status; // "DRAFT", "SUBMITTED", "APPROVED"
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public LocationVisit() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.visitDate = LocalDateTime.now();
        this.status = "DRAFT";
    }
    
    public LocationVisit(Long officerId, String location, String formType, String formData) {
        this();
        this.officerId = officerId;
        this.location = location;
        this.formType = formType;
        this.formData = formData;
    }
    
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
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
