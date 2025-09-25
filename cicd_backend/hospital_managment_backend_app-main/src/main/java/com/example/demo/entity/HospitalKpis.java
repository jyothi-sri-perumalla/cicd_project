package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hospital_kpis")
public class HospitalKpis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "officer_id", nullable = false)
    private Long officerId;
    
    @Column(name = "location", nullable = false)
    private String location;
    
    @Column(name = "month", nullable = false)
    private String month; // Format: YYYY-MM
    
    // Immunization Data
    @Column(name = "polio_doses")
    private Integer polioDoses;
    
    @Column(name = "dpt_doses")
    private Integer dptDoses;
    
    @Column(name = "bcg_doses")
    private Integer bcgDoses;
    
    @Column(name = "measles_doses")
    private Integer measlesDoses;
    
    @Column(name = "hepatitis_doses")
    private Integer hepatitisDoses;
    
    @Column(name = "tetanus_doses")
    private Integer tetanusDoses;
    
    @Column(name = "covid_doses")
    private Integer covidDoses;
    
    @Column(name = "total_immunization")
    private Integer totalImmunization;
    
    // Sterilization Data
    @Column(name = "family_planning_procedures")
    private Integer familyPlanningProcedures;
    
    @Column(name = "iud_insertions")
    private Integer iudInsertions;
    
    @Column(name = "contraceptive_counseling")
    private Integer contraceptiveCounseling;
    
    @Column(name = "sterilization_procedures")
    private Integer sterilizationProcedures;
    
    @Column(name = "total_sterilization")
    private Integer totalSterilization;
    
    // OP Treatment Data
    @Column(name = "general_consultation")
    private Integer generalConsultation;
    
    @Column(name = "specialist_consultation")
    private Integer specialistConsultation;
    
    @Column(name = "emergency_treatment")
    private Integer emergencyTreatment;
    
    @Column(name = "follow_up_visits")
    private Integer followUpVisits;
    
    @Column(name = "total_op_treatment")
    private Integer totalOpTreatment;
    
    // IP Treatment Data
    @Column(name = "medical_admissions")
    private Integer medicalAdmissions;
    
    @Column(name = "surgical_admissions")
    private Integer surgicalAdmissions;
    
    @Column(name = "pediatric_admissions")
    private Integer pediatricAdmissions;
    
    @Column(name = "maternity_admissions")
    private Integer maternityAdmissions;
    
    @Column(name = "total_ip_treatment")
    private Integer totalIpTreatment;
    
    // Surgery Data
    @Column(name = "major_surgeries")
    private Integer majorSurgeries;
    
    @Column(name = "minor_surgeries")
    private Integer minorSurgeries;
    
    @Column(name = "emergency_surgeries")
    private Integer emergencySurgeries;
    
    @Column(name = "laparoscopic_surgeries")
    private Integer laparoscopicSurgeries;
    
    @Column(name = "total_surgeries")
    private Integer totalSurgeries;
    
    // Investigation Data
    @Column(name = "blood_tests")
    private Integer bloodTests;
    
    @Column(name = "urine_tests")
    private Integer urineTests;
    
    @Column(name = "x_rays")
    private Integer xRays;
    
    @Column(name = "ultrasounds")
    private Integer ultrasounds;
    
    @Column(name = "ct_scans")
    private Integer ctScans;
    
    @Column(name = "mri_scans")
    private Integer mriScans;
    
    @Column(name = "ecg_tests")
    private Integer ecgTests;
    
    @Column(name = "total_investigations")
    private Integer totalInvestigations;
    
    // Delivery Data
    @Column(name = "normal_deliveries")
    private Integer normalDeliveries;
    
    @Column(name = "cesarean_deliveries")
    private Integer cesareanDeliveries;
    
    @Column(name = "assisted_deliveries")
    private Integer assistedDeliveries;
    
    @Column(name = "total_deliveries")
    private Integer totalDeliveries;
    
    // Dental Data
    @Column(name = "dental_consultations")
    private Integer dentalConsultations;
    
    @Column(name = "tooth_extractions")
    private Integer toothExtractions;
    
    @Column(name = "dental_fillings")
    private Integer dentalFillings;
    
    @Column(name = "dental_cleanings")
    private Integer dentalCleanings;
    
    @Column(name = "total_dental_procedures")
    private Integer totalDentalProcedures;
    
    // Emergency Data
    @Column(name = "trauma_cases")
    private Integer traumaCases;
    
    @Column(name = "cardiac_emergencies")
    private Integer cardiacEmergencies;
    
    @Column(name = "respiratory_emergencies")
    private Integer respiratoryEmergencies;
    
    @Column(name = "poisoning_cases")
    private Integer poisoningCases;
    
    @Column(name = "total_emergency_cases")
    private Integer totalEmergencyCases;
    
    // Quality Metrics
    @Column(name = "patient_satisfaction_score", columnDefinition = "DECIMAL(5,2)")
    private Double patientSatisfactionScore;
    
    @Column(name = "average_waiting_time", columnDefinition = "DECIMAL(5,2)")
    private Double averageWaitingTime;
    
    @Column(name = "bed_occupancy_rate", columnDefinition = "DECIMAL(5,2)")
    private Double bedOccupancyRate;
    
    @Column(name = "infection_control_score", columnDefinition = "DECIMAL(5,2)")
    private Double infectionControlScore;
    
    @Column(name = "staff_efficiency_rating", columnDefinition = "DECIMAL(5,2)")
    private Double staffEfficiencyRating;
    
    // Financial Information
    @Column(name = "total_revenue", columnDefinition = "DECIMAL(12,2)")
    private Double totalRevenue;
    
    @Column(name = "operational_costs", columnDefinition = "DECIMAL(12,2)")
    private Double operationalCosts;
    
    @Column(name = "equipment_costs", columnDefinition = "DECIMAL(10,2)")
    private Double equipmentCosts;
    
    @Column(name = "medicine_costs", columnDefinition = "DECIMAL(10,2)")
    private Double medicineCosts;
    
    // Additional Information
    @Column(name = "special_programs", columnDefinition = "TEXT")
    private String specialPrograms;
    
    @Column(name = "challenges_faced", columnDefinition = "TEXT")
    private String challengesFaced;
    
    @Column(name = "improvement_suggestions", columnDefinition = "TEXT")
    private String improvementSuggestions;
    
    @Column(name = "status")
    private String status; // "DRAFT", "SUBMITTED", "APPROVED", "REJECTED"
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public HospitalKpis() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = "DRAFT";
    }
    
    public HospitalKpis(Long officerId, String location, String month) {
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
    
    // Immunization Getters/Setters
    public Integer getPolioDoses() { return polioDoses; }
    public void setPolioDoses(Integer polioDoses) { this.polioDoses = polioDoses; }
    
    public Integer getDptDoses() { return dptDoses; }
    public void setDptDoses(Integer dptDoses) { this.dptDoses = dptDoses; }
    
    public Integer getBcgDoses() { return bcgDoses; }
    public void setBcgDoses(Integer bcgDoses) { this.bcgDoses = bcgDoses; }
    
    public Integer getMeaslesDoses() { return measlesDoses; }
    public void setMeaslesDoses(Integer measlesDoses) { this.measlesDoses = measlesDoses; }
    
    public Integer getHepatitisDoses() { return hepatitisDoses; }
    public void setHepatitisDoses(Integer hepatitisDoses) { this.hepatitisDoses = hepatitisDoses; }
    
    public Integer getTetanusDoses() { return tetanusDoses; }
    public void setTetanusDoses(Integer tetanusDoses) { this.tetanusDoses = tetanusDoses; }
    
    public Integer getCovidDoses() { return covidDoses; }
    public void setCovidDoses(Integer covidDoses) { this.covidDoses = covidDoses; }
    
    public Integer getTotalImmunization() { return totalImmunization; }
    public void setTotalImmunization(Integer totalImmunization) { this.totalImmunization = totalImmunization; }
    
    // Add remaining getters/setters for all other fields...
    
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
