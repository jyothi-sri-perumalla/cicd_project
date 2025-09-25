package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "esi_med6_forms")
public class EsiMed6 {
    
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
    
    // Treatment Summary
    @Column(name = "op_new_cases")
    private Integer opNewCases;
    
    @Column(name = "op_old_cases")
    private Integer opOldCases;
    
    @Column(name = "ip_new_cases")
    private Integer ipNewCases;
    
    @Column(name = "ip_old_cases")
    private Integer ipOldCases;
    
    @Column(name = "total_treatments")
    private Integer totalTreatments;
    
    // Medical Services
    @Column(name = "specialist_consultations")
    private Integer specialistConsultations;
    
    @Column(name = "emergency_cases")
    private Integer emergencyCases;
    
    @Column(name = "surgical_procedures")
    private Integer surgicalProcedures;
    
    @Column(name = "diagnostic_tests")
    private Integer diagnosticTests;
    
    @Column(name = "physiotherapy_sessions")
    private Integer physiotherapySessions;
    
    // Medications and Supplies
    @Column(name = "medicines_dispensed")
    private Integer medicinesDispensed;
    
    @Column(name = "medical_supplies_used")
    private Integer medicalSuppliesUsed;
    
    // Financial Information
    @Column(name = "total_expenditure", columnDefinition = "DECIMAL(10,2)")
    private Double totalExpenditure;
    
    @Column(name = "medicine_cost", columnDefinition = "DECIMAL(10,2)")
    private Double medicineCost;
    
    @Column(name = "equipment_cost", columnDefinition = "DECIMAL(10,2)")
    private Double equipmentCost;
    
    // Additional Notes
    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;
    
    // Disease Data - Store all 136 diseases as JSON
    @Column(name = "disease_data", columnDefinition = "LONGTEXT")
    private String diseaseData;
    
    // Certificate Information
    @Column(name = "total_insured_persons")
    private Integer totalInsuredPersons;
    
    @Column(name = "first_certificates")
    private Integer firstCertificates;
    
    @Column(name = "first_final_certificates")
    private Integer firstFinalCertificates;
    
    @Column(name = "final_certificates")
    private Integer finalCertificates;
    
    @Column(name = "intermediate_certificates")
    private Integer intermediateCertificates;
    
    @Column(name = "special_intermediate_certificates")
    private Integer specialIntermediateCertificates;
    
    @Column(name = "days_certified")
    private Integer daysCertified;
    
    @Column(name = "injury_reports")
    private Integer injuryReports;
    
    @Column(name = "xrays_done")
    private Integer xraysDone;
    
    @Column(name = "laboratory_referrals")
    private Integer laboratoryReferrals;
    
    @Column(name = "radio_imaging_referrals")
    private Integer radioImagingReferrals;
    
    @Column(name = "medical_referee_referrals")
    private Integer medicalRefereeReferrals;
    
    @Column(name = "status")
    private String status; // "DRAFT", "SUBMITTED", "APPROVED", "REJECTED"
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public EsiMed6() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = "DRAFT";
    }
    
    public EsiMed6(Long officerId, String location, String month) {
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
    
    public Integer getOpNewCases() { return opNewCases; }
    public void setOpNewCases(Integer opNewCases) { this.opNewCases = opNewCases; }
    
    public Integer getOpOldCases() { return opOldCases; }
    public void setOpOldCases(Integer opOldCases) { this.opOldCases = opOldCases; }
    
    public Integer getIpNewCases() { return ipNewCases; }
    public void setIpNewCases(Integer ipNewCases) { this.ipNewCases = ipNewCases; }
    
    public Integer getIpOldCases() { return ipOldCases; }
    public void setIpOldCases(Integer ipOldCases) { this.ipOldCases = ipOldCases; }
    
    public Integer getTotalTreatments() { return totalTreatments; }
    public void setTotalTreatments(Integer totalTreatments) { this.totalTreatments = totalTreatments; }
    
    public Integer getSpecialistConsultations() { return specialistConsultations; }
    public void setSpecialistConsultations(Integer specialistConsultations) { this.specialistConsultations = specialistConsultations; }
    
    public Integer getEmergencyCases() { return emergencyCases; }
    public void setEmergencyCases(Integer emergencyCases) { this.emergencyCases = emergencyCases; }
    
    public Integer getSurgicalProcedures() { return surgicalProcedures; }
    public void setSurgicalProcedures(Integer surgicalProcedures) { this.surgicalProcedures = surgicalProcedures; }
    
    public Integer getDiagnosticTests() { return diagnosticTests; }
    public void setDiagnosticTests(Integer diagnosticTests) { this.diagnosticTests = diagnosticTests; }
    
    public Integer getPhysiotherapySessions() { return physiotherapySessions; }
    public void setPhysiotherapySessions(Integer physiotherapySessions) { this.physiotherapySessions = physiotherapySessions; }
    
    public Integer getMedicinesDispensed() { return medicinesDispensed; }
    public void setMedicinesDispensed(Integer medicinesDispensed) { this.medicinesDispensed = medicinesDispensed; }
    
    public Integer getMedicalSuppliesUsed() { return medicalSuppliesUsed; }
    public void setMedicalSuppliesUsed(Integer medicalSuppliesUsed) { this.medicalSuppliesUsed = medicalSuppliesUsed; }
    
    public Double getTotalExpenditure() { return totalExpenditure; }
    public void setTotalExpenditure(Double totalExpenditure) { this.totalExpenditure = totalExpenditure; }
    
    public Double getMedicineCost() { return medicineCost; }
    public void setMedicineCost(Double medicineCost) { this.medicineCost = medicineCost; }
    
    public Double getEquipmentCost() { return equipmentCost; }
    public void setEquipmentCost(Double equipmentCost) { this.equipmentCost = equipmentCost; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public String getDiseaseData() { return diseaseData; }
    public void setDiseaseData(String diseaseData) { this.diseaseData = diseaseData; }
    
    public Integer getTotalInsuredPersons() { return totalInsuredPersons; }
    public void setTotalInsuredPersons(Integer totalInsuredPersons) { this.totalInsuredPersons = totalInsuredPersons; }
    
    public Integer getFirstCertificates() { return firstCertificates; }
    public void setFirstCertificates(Integer firstCertificates) { this.firstCertificates = firstCertificates; }
    
    public Integer getFirstFinalCertificates() { return firstFinalCertificates; }
    public void setFirstFinalCertificates(Integer firstFinalCertificates) { this.firstFinalCertificates = firstFinalCertificates; }
    
    public Integer getFinalCertificates() { return finalCertificates; }
    public void setFinalCertificates(Integer finalCertificates) { this.finalCertificates = finalCertificates; }
    
    public Integer getIntermediateCertificates() { return intermediateCertificates; }
    public void setIntermediateCertificates(Integer intermediateCertificates) { this.intermediateCertificates = intermediateCertificates; }
    
    public Integer getSpecialIntermediateCertificates() { return specialIntermediateCertificates; }
    public void setSpecialIntermediateCertificates(Integer specialIntermediateCertificates) { this.specialIntermediateCertificates = specialIntermediateCertificates; }
    
    public Integer getDaysCertified() { return daysCertified; }
    public void setDaysCertified(Integer daysCertified) { this.daysCertified = daysCertified; }
    
    public Integer getInjuryReports() { return injuryReports; }
    public void setInjuryReports(Integer injuryReports) { this.injuryReports = injuryReports; }
    
    public Integer getXraysDone() { return xraysDone; }
    public void setXraysDone(Integer xraysDone) { this.xraysDone = xraysDone; }
    
    public Integer getLaboratoryReferrals() { return laboratoryReferrals; }
    public void setLaboratoryReferrals(Integer laboratoryReferrals) { this.laboratoryReferrals = laboratoryReferrals; }
    
    public Integer getRadioImagingReferrals() { return radioImagingReferrals; }
    public void setRadioImagingReferrals(Integer radioImagingReferrals) { this.radioImagingReferrals = radioImagingReferrals; }
    
    public Integer getMedicalRefereeReferrals() { return medicalRefereeReferrals; }
    public void setMedicalRefereeReferrals(Integer medicalRefereeReferrals) { this.medicalRefereeReferrals = medicalRefereeReferrals; }
    
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
