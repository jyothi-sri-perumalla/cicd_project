package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sasa_kpis")
public class SasaKpi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "officer_id", nullable = false)
    private Long officerId;
    
    @Column(name = "location", nullable = false)
    private String location;
    
    @Column(name = "month", nullable = false)
    private String month; // Format: YYYY-MM
    
    @Column(name = "submission_sequence")
    private Integer submissionSequence; // Track multiple submissions per month
    
    // Store the complete checklist data as JSON
    @Column(name = "checklist_data", columnDefinition = "LONGTEXT")
    private String checklistData;
    
    // Individual indicators for easy querying
    @Column(name = "biomedical_waste_disposal")
    private String biomedicalWasteDisposal; // "Yes" or "No"
    
    @Column(name = "toilet_maintenance")
    private String toiletMaintenance; // "Yes" or "No"
    
    @Column(name = "safe_drinking_water")
    private String safeDrinkingWater; // "Yes" or "No"
    
    @Column(name = "sanitation_worker_training")
    private String sanitationWorkerTraining; // "Yes" or "No"
    
    @Column(name = "staff_hygiene_training")
    private String staffHygieneTraining; // "Yes" or "No"
    
    // Summary statistics
    @Column(name = "total_indicators")
    private Integer totalIndicators;
    
    @Column(name = "yes_responses")
    private Integer yesResponses;
    
    @Column(name = "no_responses")
    private Integer noResponses;
    
    @Column(name = "compliance_percentage")
    private Double compliancePercentage;
    
    @Column(name = "status")
    private String status; // "DRAFT", "SUBMITTED", "APPROVED", "REJECTED"
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public SasaKpi() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = "DRAFT";
    }
    
    public SasaKpi(Long officerId, String location, String month) {
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
    
    public Integer getSubmissionSequence() { return submissionSequence; }
    public void setSubmissionSequence(Integer submissionSequence) { this.submissionSequence = submissionSequence; }
    
    public String getChecklistData() { return checklistData; }
    public void setChecklistData(String checklistData) { this.checklistData = checklistData; }
    
    public String getBiomedicalWasteDisposal() { return biomedicalWasteDisposal; }
    public void setBiomedicalWasteDisposal(String biomedicalWasteDisposal) { this.biomedicalWasteDisposal = biomedicalWasteDisposal; }
    
    public String getToiletMaintenance() { return toiletMaintenance; }
    public void setToiletMaintenance(String toiletMaintenance) { this.toiletMaintenance = toiletMaintenance; }
    
    public String getSafeDrinkingWater() { return safeDrinkingWater; }
    public void setSafeDrinkingWater(String safeDrinkingWater) { this.safeDrinkingWater = safeDrinkingWater; }
    
    public String getSanitationWorkerTraining() { return sanitationWorkerTraining; }
    public void setSanitationWorkerTraining(String sanitationWorkerTraining) { this.sanitationWorkerTraining = sanitationWorkerTraining; }
    
    public String getStaffHygieneTraining() { return staffHygieneTraining; }
    public void setStaffHygieneTraining(String staffHygieneTraining) { this.staffHygieneTraining = staffHygieneTraining; }
    
    public Integer getTotalIndicators() { return totalIndicators; }
    public void setTotalIndicators(Integer totalIndicators) { this.totalIndicators = totalIndicators; }
    
    public Integer getYesResponses() { return yesResponses; }
    public void setYesResponses(Integer yesResponses) { this.yesResponses = yesResponses; }
    
    public Integer getNoResponses() { return noResponses; }
    public void setNoResponses(Integer noResponses) { this.noResponses = noResponses; }
    
    public Double getCompliancePercentage() { return compliancePercentage; }
    public void setCompliancePercentage(Double compliancePercentage) { this.compliancePercentage = compliancePercentage; }
    
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
