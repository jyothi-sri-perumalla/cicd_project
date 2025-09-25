package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dc_kpis")
public class DcKpis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "officer_id", nullable = false)
    private Long officerId;
    
    @Column(name = "location", nullable = false)
    private String location;
    
    @Column(name = "month", nullable = false)
    private String month; // Format: YYYY-MM
    
    // DC KPI Data
    @Column(name = "immunization")
    private Integer immunization;
    
    @Column(name = "sterilization")
    private Integer sterilization;
    
    @Column(name = "op_treatment")
    private Integer opTreatment;
    
    @Column(name = "ip_treatment")
    private Integer ipTreatment;
    
    @Column(name = "bed_occupancy")
    private Integer bedOccupancy;
    
    @Column(name = "lab_investigations")
    private Integer labInvestigations;
    
    @Column(name = "health_camps")
    private Integer healthCamps;
    
    @Column(name = "status")
    private String status; // "DRAFT", "SUBMITTED", "APPROVED", "REJECTED"
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public DcKpis() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = "DRAFT";
    }
    
    public DcKpis(Long officerId, String location, String month) {
        this();
        this.officerId = officerId;
        this.location = location;
        this.month = month;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getOfficerId() { return officerId; }
    public void setOfficerId(Long officerId) { this.officerId = officerId; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    
    public Integer getImmunization() { return immunization; }
    public void setImmunization(Integer immunization) { this.immunization = immunization; }
    
    public Integer getSterilization() { return sterilization; }
    public void setSterilization(Integer sterilization) { this.sterilization = sterilization; }
    
    public Integer getOpTreatment() { return opTreatment; }
    public void setOpTreatment(Integer opTreatment) { this.opTreatment = opTreatment; }
    
    public Integer getIpTreatment() { return ipTreatment; }
    public void setIpTreatment(Integer ipTreatment) { this.ipTreatment = ipTreatment; }
    
    public Integer getBedOccupancy() { return bedOccupancy; }
    public void setBedOccupancy(Integer bedOccupancy) { this.bedOccupancy = bedOccupancy; }
    
    public Integer getLabInvestigations() { return labInvestigations; }
    public void setLabInvestigations(Integer labInvestigations) { this.labInvestigations = labInvestigations; }
    
    public Integer getHealthCamps() { return healthCamps; }
    public void setHealthCamps(Integer healthCamps) { this.healthCamps = healthCamps; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
