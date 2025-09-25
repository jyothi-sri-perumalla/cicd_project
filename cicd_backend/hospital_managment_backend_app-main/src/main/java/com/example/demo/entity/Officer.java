package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "officers")
public class Officer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;
    
    @Column(name = "password_hash")
    private String passwordHash;
    
    @Column(name = "password_set")
    private Boolean passwordSet;
    
    @ElementCollection
    @CollectionTable(name = "officer_locations", joinColumns = @JoinColumn(name = "officer_id"))
    @Column(name = "location")
    private List<String> locations;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    // Constructors
    public Officer() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isActive = true;
        this.passwordSet = false;
    }
    
    public Officer(String fullName, String email, String mobileNumber, List<String> locations) {
        this();
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.locations = locations;
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
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }
    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public Boolean getPasswordSet() {
        return passwordSet;
    }
    
    public void setPasswordSet(Boolean passwordSet) {
        this.passwordSet = passwordSet;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
