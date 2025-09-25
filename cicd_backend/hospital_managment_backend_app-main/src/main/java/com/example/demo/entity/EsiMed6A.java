package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "esi_med6a_forms")
public class EsiMed6A {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "officer_id", nullable = false)
    private Long officerId;
    
    @Column(name = "location", nullable = false)
    private String location;
    
    @Column(name = "month", nullable = false)
    private String month; // Format: YYYY-MM
    
    // Institution Information
    @Column(name = "institution_name")
    private String institutionName;
    
    @Column(name = "institution_code")
    private String institutionCode;
    
    @Column(name = "region")
    private String region;
    
    @Column(name = "reporting_period")
    private String reportingPeriod;
    
    // Advanced Medical Services
    @Column(name = "icu_admissions")
    private Integer icuAdmissions;
    
    @Column(name = "ventilator_usage_hours")
    private Integer ventilatorUsageHours;
    
    @Column(name = "major_surgeries")
    private Integer majorSurgeries;
    
    @Column(name = "minor_surgeries")
    private Integer minorSurgeries;
    
    @Column(name = "emergency_surgeries")
    private Integer emergencySurgeries;
    
    // Specialized Departments
    @Column(name = "cardiology_cases")
    private Integer cardiologyCases;
    
    @Column(name = "nephrology_cases")
    private Integer nephrologyCases;
    
    @Column(name = "oncology_cases")
    private Integer oncologyCases;
    
    @Column(name = "neurology_cases")
    private Integer neurologyCases;
    
    @Column(name = "orthopedic_cases")
    private Integer orthopedicCases;
    
    // Laboratory Services
    @Column(name = "blood_tests")
    private Integer bloodTests;
    
    @Column(name = "urine_tests")
    private Integer urineTests;
    
    @Column(name = "radiology_xray")
    private Integer radiologyXray;
    
    @Column(name = "ct_scans")
    private Integer ctScans;
    
    @Column(name = "mri_scans")
    private Integer mriScans;
    
    @Column(name = "ultrasound_scans")
    private Integer ultrasoundScans;
    
    // Pharmacy Services
    @Column(name = "prescriptions_filled")
    private Integer prescriptionsFilled;
    
    @Column(name = "controlled_substances")
    private Integer controlledSubstances;
    
    @Column(name = "vaccines_administered")
    private Integer vaccinesAdministered;
    
    // Quality Metrics
    @Column(name = "patient_satisfaction_score", columnDefinition = "DECIMAL(3,2)")
    private Double patientSatisfactionScore;
    
    @Column(name = "infection_control_score", columnDefinition = "DECIMAL(3,2)")
    private Double infectionControlScore;
    
    @Column(name = "staff_efficiency_rating", columnDefinition = "DECIMAL(3,2)")
    private Double staffEfficiencyRating;
    
    // Financial Metrics
    @Column(name = "revenue_generated", columnDefinition = "DECIMAL(12,2)")
    private Double revenueGenerated;
    
    @Column(name = "operational_costs", columnDefinition = "DECIMAL(12,2)")
    private Double operationalCosts;
    
    @Column(name = "equipment_maintenance_cost", columnDefinition = "DECIMAL(10,2)")
    private Double equipmentMaintenanceCost;
    
    // Staffing Information
    @Column(name = "doctors_count")
    private Integer doctorsCount;
    
    @Column(name = "nurses_count")
    private Integer nursesCount;
    
    @Column(name = "technicians_count")
    private Integer techniciansCount;
    
    @Column(name = "administrative_staff_count")
    private Integer administrativeStaffCount;
    
    // Additional Information
    @Column(name = "special_programs", columnDefinition = "TEXT")
    private String specialPrograms;
    
    @Column(name = "challenges_faced", columnDefinition = "TEXT")
    private String challengesFaced;
    
    @Column(name = "improvement_suggestions", columnDefinition = "TEXT")
    private String improvementSuggestions;
    
    // Disease Data - JSON string containing all disease information
    @Column(name = "disease_data", columnDefinition = "LONGTEXT")
    private String diseaseData;
    
    @Column(name = "status")
    private String status; // "DRAFT", "SUBMITTED", "APPROVED", "REJECTED"
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public EsiMed6A() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = "DRAFT";
    }
    
    public EsiMed6A(Long officerId, String location, String month) {
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
    
    public String getInstitutionName() { return institutionName; }
    public void setInstitutionName(String institutionName) { this.institutionName = institutionName; }
    
    public String getInstitutionCode() { return institutionCode; }
    public void setInstitutionCode(String institutionCode) { this.institutionCode = institutionCode; }
    
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    
    public String getReportingPeriod() { return reportingPeriod; }
    public void setReportingPeriod(String reportingPeriod) { this.reportingPeriod = reportingPeriod; }
    
    public Integer getIcuAdmissions() { return icuAdmissions; }
    public void setIcuAdmissions(Integer icuAdmissions) { this.icuAdmissions = icuAdmissions; }
    
    public Integer getVentilatorUsageHours() { return ventilatorUsageHours; }
    public void setVentilatorUsageHours(Integer ventilatorUsageHours) { this.ventilatorUsageHours = ventilatorUsageHours; }
    
    public Integer getMajorSurgeries() { return majorSurgeries; }
    public void setMajorSurgeries(Integer majorSurgeries) { this.majorSurgeries = majorSurgeries; }
    
    public Integer getMinorSurgeries() { return minorSurgeries; }
    public void setMinorSurgeries(Integer minorSurgeries) { this.minorSurgeries = minorSurgeries; }
    
    public Integer getEmergencySurgeries() { return emergencySurgeries; }
    public void setEmergencySurgeries(Integer emergencySurgeries) { this.emergencySurgeries = emergencySurgeries; }
    
    public Integer getCardiologyCases() { return cardiologyCases; }
    public void setCardiologyCases(Integer cardiologyCases) { this.cardiologyCases = cardiologyCases; }
    
    public Integer getNephrologyCases() { return nephrologyCases; }
    public void setNephrologyCases(Integer nephrologyCases) { this.nephrologyCases = nephrologyCases; }
    
    public Integer getOncologyCases() { return oncologyCases; }
    public void setOncologyCases(Integer oncologyCases) { this.oncologyCases = oncologyCases; }
    
    public Integer getNeurologyCases() { return neurologyCases; }
    public void setNeurologyCases(Integer neurologyCases) { this.neurologyCases = neurologyCases; }
    
    public Integer getOrthopedicCases() { return orthopedicCases; }
    public void setOrthopedicCases(Integer orthopedicCases) { this.orthopedicCases = orthopedicCases; }
    
    public Integer getBloodTests() { return bloodTests; }
    public void setBloodTests(Integer bloodTests) { this.bloodTests = bloodTests; }
    
    public Integer getUrineTests() { return urineTests; }
    public void setUrineTests(Integer urineTests) { this.urineTests = urineTests; }
    
    public Integer getRadiologyXray() { return radiologyXray; }
    public void setRadiologyXray(Integer radiologyXray) { this.radiologyXray = radiologyXray; }
    
    public Integer getCtScans() { return ctScans; }
    public void setCtScans(Integer ctScans) { this.ctScans = ctScans; }
    
    public Integer getMriScans() { return mriScans; }
    public void setMriScans(Integer mriScans) { this.mriScans = mriScans; }
    
    public Integer getUltrasoundScans() { return ultrasoundScans; }
    public void setUltrasoundScans(Integer ultrasoundScans) { this.ultrasoundScans = ultrasoundScans; }
    
    public Integer getPrescriptionsFilled() { return prescriptionsFilled; }
    public void setPrescriptionsFilled(Integer prescriptionsFilled) { this.prescriptionsFilled = prescriptionsFilled; }
    
    public Integer getControlledSubstances() { return controlledSubstances; }
    public void setControlledSubstances(Integer controlledSubstances) { this.controlledSubstances = controlledSubstances; }
    
    public Integer getVaccinesAdministered() { return vaccinesAdministered; }
    public void setVaccinesAdministered(Integer vaccinesAdministered) { this.vaccinesAdministered = vaccinesAdministered; }
    
    public Double getPatientSatisfactionScore() { return patientSatisfactionScore; }
    public void setPatientSatisfactionScore(Double patientSatisfactionScore) { this.patientSatisfactionScore = patientSatisfactionScore; }
    
    public Double getInfectionControlScore() { return infectionControlScore; }
    public void setInfectionControlScore(Double infectionControlScore) { this.infectionControlScore = infectionControlScore; }
    
    public Double getStaffEfficiencyRating() { return staffEfficiencyRating; }
    public void setStaffEfficiencyRating(Double staffEfficiencyRating) { this.staffEfficiencyRating = staffEfficiencyRating; }
    
    public Double getRevenueGenerated() { return revenueGenerated; }
    public void setRevenueGenerated(Double revenueGenerated) { this.revenueGenerated = revenueGenerated; }
    
    public Double getOperationalCosts() { return operationalCosts; }
    public void setOperationalCosts(Double operationalCosts) { this.operationalCosts = operationalCosts; }
    
    public Double getEquipmentMaintenanceCost() { return equipmentMaintenanceCost; }
    public void setEquipmentMaintenanceCost(Double equipmentMaintenanceCost) { this.equipmentMaintenanceCost = equipmentMaintenanceCost; }
    
    public Integer getDoctorsCount() { return doctorsCount; }
    public void setDoctorsCount(Integer doctorsCount) { this.doctorsCount = doctorsCount; }
    
    public Integer getNursesCount() { return nursesCount; }
    public void setNursesCount(Integer nursesCount) { this.nursesCount = nursesCount; }
    
    public Integer getTechniciansCount() { return techniciansCount; }
    public void setTechniciansCount(Integer techniciansCount) { this.techniciansCount = techniciansCount; }
    
    public Integer getAdministrativeStaffCount() { return administrativeStaffCount; }
    public void setAdministrativeStaffCount(Integer administrativeStaffCount) { this.administrativeStaffCount = administrativeStaffCount; }
    
    public String getSpecialPrograms() { return specialPrograms; }
    public void setSpecialPrograms(String specialPrograms) { this.specialPrograms = specialPrograms; }
    
    public String getChallengesFaced() { return challengesFaced; }
    public void setChallengesFaced(String challengesFaced) { this.challengesFaced = challengesFaced; }
    
    public String getImprovementSuggestions() { return improvementSuggestions; }
    public void setImprovementSuggestions(String improvementSuggestions) { this.improvementSuggestions = improvementSuggestions; }
    
    public String getDiseaseData() { return diseaseData; }
    public void setDiseaseData(String diseaseData) { this.diseaseData = diseaseData; }
    
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
