package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "kpi_reports")
public class KpiReport {
    
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
    
    // Store the complete report data as JSON
    @Column(name = "report_data", columnDefinition = "LONGTEXT")
    private String reportData;
    
    // Summary statistics
    @Column(name = "total_dispensaries")
    private Integer totalDispensaries;
    
    @Column(name = "total_ips_attached")
    private Integer totalIpsAttached;
    
    @Column(name = "total_online_registrations")
    private Integer totalOnlineRegistrations;
    
    @Column(name = "total_offline_registrations")
    private Integer totalOfflineRegistrations;
    
    @Column(name = "total_registrations")
    private Integer totalRegistrations;
    
    @Column(name = "total_avg_daily_opd")
    private Integer totalAvgDailyOpd;
    
    @Column(name = "total_sterilization")
    private Integer totalSterilization;
    
    @Column(name = "total_immunization")
    private Integer totalImmunization;
    
    @Column(name = "total_lab_investigations")
    private Integer totalLabInvestigations;
    
    @Column(name = "status")
    private String status; // "DRAFT", "SUBMITTED", "APPROVED", "REJECTED"
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public KpiReport() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = "DRAFT";
    }
    
    public KpiReport(Long officerId, String location, String month) {
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
    
    public String getReportData() { return reportData; }
    public void setReportData(String reportData) { this.reportData = reportData; }
    
    public Integer getTotalDispensaries() { return totalDispensaries; }
    public void setTotalDispensaries(Integer totalDispensaries) { this.totalDispensaries = totalDispensaries; }
    
    public Integer getTotalIpsAttached() { return totalIpsAttached; }
    public void setTotalIpsAttached(Integer totalIpsAttached) { this.totalIpsAttached = totalIpsAttached; }
    
    public Integer getTotalOnlineRegistrations() { return totalOnlineRegistrations; }
    public void setTotalOnlineRegistrations(Integer totalOnlineRegistrations) { this.totalOnlineRegistrations = totalOnlineRegistrations; }
    
    public Integer getTotalOfflineRegistrations() { return totalOfflineRegistrations; }
    public void setTotalOfflineRegistrations(Integer totalOfflineRegistrations) { this.totalOfflineRegistrations = totalOfflineRegistrations; }
    
    public Integer getTotalRegistrations() { return totalRegistrations; }
    public void setTotalRegistrations(Integer totalRegistrations) { this.totalRegistrations = totalRegistrations; }
    
    public Integer getTotalAvgDailyOpd() { return totalAvgDailyOpd; }
    public void setTotalAvgDailyOpd(Integer totalAvgDailyOpd) { this.totalAvgDailyOpd = totalAvgDailyOpd; }
    
    public Integer getTotalSterilization() { return totalSterilization; }
    public void setTotalSterilization(Integer totalSterilization) { this.totalSterilization = totalSterilization; }
    
    public Integer getTotalImmunization() { return totalImmunization; }
    public void setTotalImmunization(Integer totalImmunization) { this.totalImmunization = totalImmunization; }
    
    public Integer getTotalLabInvestigations() { return totalLabInvestigations; }
    public void setTotalLabInvestigations(Integer totalLabInvestigations) { this.totalLabInvestigations = totalLabInvestigations; }
    
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
