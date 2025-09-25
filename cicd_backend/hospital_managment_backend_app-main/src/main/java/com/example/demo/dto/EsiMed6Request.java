package com.example.demo.dto;

public class EsiMed6Request {
    
    private Long officerId;
    private String location;
    private String month;
    
    // Institution Information
    private String institutionName;
    private String institutionCode;
    private String region;
    
    // Treatment Summary
    private Integer opNewCases;
    private Integer opOldCases;
    private Integer ipNewCases;
    private Integer ipOldCases;
    private Integer totalTreatments;
    
    // Medical Services
    private Integer specialistConsultations;
    private Integer emergencyCases;
    private Integer surgicalProcedures;
    private Integer diagnosticTests;
    private Integer physiotherapySessions;
    
    // Medications and Supplies
    private Integer medicinesDispensed;
    private Integer medicalSuppliesUsed;
    
    // Financial Information
    private Double totalExpenditure;
    private Double medicineCost;
    private Double equipmentCost;
    
    // Additional Notes
    private String remarks;
    
    // Constructors
    public EsiMed6Request() {}
    
    // Getters and Setters
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
}
