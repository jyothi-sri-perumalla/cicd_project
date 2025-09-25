package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "esi_med9_forms")
public class EsiMed9 {
    
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
    
    // Disease Categories (298 disease codes)
    @Column(name = "infectious_diseases")
    private Integer infectiousDiseases;
    
    @Column(name = "parasitic_diseases")
    private Integer parasiticDiseases;
    
    @Column(name = "neoplasms")
    private Integer neoplasms;
    
    @Column(name = "blood_disorders")
    private Integer bloodDisorders;
    
    @Column(name = "endocrine_disorders")
    private Integer endocrineDisorders;
    
    @Column(name = "mental_disorders")
    private Integer mentalDisorders;
    
    @Column(name = "nervous_system_diseases")
    private Integer nervousSystemDiseases;
    
    @Column(name = "eye_diseases")
    private Integer eyeDiseases;
    
    @Column(name = "ear_diseases")
    private Integer earDiseases;
    
    @Column(name = "circulatory_diseases")
    private Integer circulatoryDiseases;
    
    @Column(name = "respiratory_diseases")
    private Integer respiratoryDiseases;
    
    @Column(name = "digestive_diseases")
    private Integer digestiveDiseases;
    
    @Column(name = "skin_diseases")
    private Integer skinDiseases;
    
    @Column(name = "musculoskeletal_diseases")
    private Integer musculoskeletalDiseases;
    
    @Column(name = "genitourinary_diseases")
    private Integer genitourinaryDiseases;
    
    @Column(name = "pregnancy_complications")
    private Integer pregnancyComplications;
    
    @Column(name = "perinatal_conditions")
    private Integer perinatalConditions;
    
    @Column(name = "congenital_anomalies")
    private Integer congenitalAnomalies;
    
    @Column(name = "injuries_poisoning")
    private Integer injuriesPoisoning;
    
    // Specialist Services
    @Column(name = "cardiology_consultations")
    private Integer cardiologyConsultations;
    
    @Column(name = "neurology_consultations")
    private Integer neurologyConsultations;
    
    @Column(name = "orthopedic_consultations")
    private Integer orthopedicConsultations;
    
    @Column(name = "pediatric_consultations")
    private Integer pediatricConsultations;
    
    @Column(name = "gynecology_consultations")
    private Integer gynecologyConsultations;
    
    @Column(name = "dermatology_consultations")
    private Integer dermatologyConsultations;
    
    @Column(name = "psychiatry_consultations")
    private Integer psychiatryConsultations;
    
    @Column(name = "ophthalmology_consultations")
    private Integer ophthalmologyConsultations;
    
    @Column(name = "ent_consultations")
    private Integer entConsultations;
    
    @Column(name = "radiology_services")
    private Integer radiologyServices;
    
    @Column(name = "pathology_services")
    private Integer pathologyServices;
    
    @Column(name = "physiotherapy_services")
    private Integer physiotherapyServices;
    
    // Monthly Statistics
    @Column(name = "total_op_patients")
    private Integer totalOpPatients;
    
    @Column(name = "total_ip_patients")
    private Integer totalIpPatients;
    
    @Column(name = "emergency_cases")
    private Integer emergencyCases;
    
    @Column(name = "surgical_procedures")
    private Integer surgicalProcedures;
    
    @Column(name = "diagnostic_procedures")
    private Integer diagnosticProcedures;
    
    @Column(name = "laboratory_tests")
    private Integer laboratoryTests;
    
    @Column(name = "imaging_studies")
    private Integer imagingStudies;
    
    // Medications and Supplies
    @Column(name = "antibiotics_prescribed")
    private Integer antibioticsPrescribed;
    
    @Column(name = "analgesics_prescribed")
    private Integer analgesicsPrescribed;
    
    @Column(name = "vaccines_administered")
    private Integer vaccinesAdministered;
    
    @Column(name = "surgical_supplies_used")
    private Integer surgicalSuppliesUsed;
    
    @Column(name = "medical_devices_used")
    private Integer medicalDevicesUsed;
    
    // Financial Information
    @Column(name = "total_revenue", columnDefinition = "DECIMAL(12,2)")
    private Double totalRevenue;
    
    @Column(name = "medicine_expenditure", columnDefinition = "DECIMAL(10,2)")
    private Double medicineExpenditure;
    
    @Column(name = "equipment_expenditure", columnDefinition = "DECIMAL(10,2)")
    private Double equipmentExpenditure;
    
    @Column(name = "staff_salaries", columnDefinition = "DECIMAL(10,2)")
    private Double staffSalaries;
    
    @Column(name = "administrative_costs", columnDefinition = "DECIMAL(10,2)")
    private Double administrativeCosts;
    
    // Quality Indicators
    @Column(name = "patient_satisfaction_score", columnDefinition = "DECIMAL(3,2)")
    private Double patientSatisfactionScore;
    
    @Column(name = "treatment_success_rate", columnDefinition = "DECIMAL(5,2)")
    private Double treatmentSuccessRate;
    
    @Column(name = "complication_rate", columnDefinition = "DECIMAL(5,2)")
    private Double complicationRate;
    
    @Column(name = "readmission_rate", columnDefinition = "DECIMAL(5,2)")
    private Double readmissionRate;
    
    @Column(name = "infection_control_score", columnDefinition = "DECIMAL(3,2)")
    private Double infectionControlScore;
    
    // Additional Information
    @Column(name = "special_campaigns", columnDefinition = "TEXT")
    private String specialCampaigns;
    
    @Column(name = "research_activities", columnDefinition = "TEXT")
    private String researchActivities;
    
    @Column(name = "training_programs", columnDefinition = "TEXT")
    private String trainingPrograms;
    
    @Column(name = "challenges_faced", columnDefinition = "TEXT")
    private String challengesFaced;
    
    @Column(name = "recommendations", columnDefinition = "TEXT")
    private String recommendations;
    
    // Patient Statistics
    @Column(name = "total_patients")
    private Integer totalPatients;
    
    @Column(name = "new_patients")
    private Integer newPatients;
    
    @Column(name = "repeat_patients")
    private Integer repeatPatients;
    
    // Hospital Operations
    @Column(name = "beds_available")
    private Integer bedsAvailable;
    
    @Column(name = "cases_admitted")
    private Integer casesAdmitted;
    
    @Column(name = "cases_discharged")
    private Integer casesDischarged;
    
    @Column(name = "total_bed_days")
    private Integer totalBedDays;
    
    @Column(name = "occupancy_percentage", columnDefinition = "DECIMAL(5,2)")
    private java.math.BigDecimal occupancyPercentage;
    
    // TB Specific Data
    @Column(name = "tb_beds")
    private Integer tbBeds;
    
    @Column(name = "tb_admitted")
    private Integer tbAdmitted;
    
    @Column(name = "tb_discharged")
    private Integer tbDischarged;
    
    // Maternity Data
    @Column(name = "confinements")
    private Integer confinements;
    
    @Column(name = "still_births")
    private Integer stillBirths;
    
    @Column(name = "maternal_deaths")
    private Integer maternalDeaths;
    
    @Column(name = "perinatal_deaths")
    private Integer perinatalDeaths;
    
    // X-ray Services
    @Column(name = "xrays_done")
    private Integer xraysDone;
    
    // Specialist Consultations
    @Column(name = "specialist_consultations")
    private Integer specialistConsultations;

    // Summary Statistics
    @Column(name = "total_opd_cases")
    private Integer totalOpdCases;
    
    @Column(name = "total_ipd_cases")
    private Integer totalIpdCases;
    
    @Column(name = "total_disease_cases")
    private Integer totalDiseaseCases;
    
    // Structured Data Storage
    @Column(name = "opd_data", columnDefinition = "TEXT")
    private String opdData;
    
    @Column(name = "pathology_data", columnDefinition = "TEXT")
    private String pathologyData;
    
    @Column(name = "hospitalisation_data", columnDefinition = "TEXT")
    private String hospitalisationData;

    // Disease Data - JSON string containing all disease information
    @Column(name = "disease_data", columnDefinition = "LONGTEXT")
    private String diseaseData;
    
    // Remarks and Comments
    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;
    
    @Column(name = "status")
    private String status; // "DRAFT", "SUBMITTED", "APPROVED", "REJECTED"
    
    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public EsiMed9() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = "DRAFT";
    }
    
    public EsiMed9(Long officerId, String location, String month) {
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
    
    public Integer getInfectiousDiseases() { return infectiousDiseases; }
    public void setInfectiousDiseases(Integer infectiousDiseases) { this.infectiousDiseases = infectiousDiseases; }
    
    public Integer getParasiticDiseases() { return parasiticDiseases; }
    public void setParasiticDiseases(Integer parasiticDiseases) { this.parasiticDiseases = parasiticDiseases; }
    
    public Integer getNeoplasms() { return neoplasms; }
    public void setNeoplasms(Integer neoplasms) { this.neoplasms = neoplasms; }
    
    public Integer getBloodDisorders() { return bloodDisorders; }
    public void setBloodDisorders(Integer bloodDisorders) { this.bloodDisorders = bloodDisorders; }
    
    public Integer getEndocrineDisorders() { return endocrineDisorders; }
    public void setEndocrineDisorders(Integer endocrineDisorders) { this.endocrineDisorders = endocrineDisorders; }
    
    public Integer getMentalDisorders() { return mentalDisorders; }
    public void setMentalDisorders(Integer mentalDisorders) { this.mentalDisorders = mentalDisorders; }
    
    public Integer getNervousSystemDiseases() { return nervousSystemDiseases; }
    public void setNervousSystemDiseases(Integer nervousSystemDiseases) { this.nervousSystemDiseases = nervousSystemDiseases; }
    
    public Integer getEyeDiseases() { return eyeDiseases; }
    public void setEyeDiseases(Integer eyeDiseases) { this.eyeDiseases = eyeDiseases; }
    
    public Integer getEarDiseases() { return earDiseases; }
    public void setEarDiseases(Integer earDiseases) { this.earDiseases = earDiseases; }
    
    public Integer getCirculatoryDiseases() { return circulatoryDiseases; }
    public void setCirculatoryDiseases(Integer circulatoryDiseases) { this.circulatoryDiseases = circulatoryDiseases; }
    
    public Integer getRespiratoryDiseases() { return respiratoryDiseases; }
    public void setRespiratoryDiseases(Integer respiratoryDiseases) { this.respiratoryDiseases = respiratoryDiseases; }
    
    public Integer getDigestiveDiseases() { return digestiveDiseases; }
    public void setDigestiveDiseases(Integer digestiveDiseases) { this.digestiveDiseases = digestiveDiseases; }
    
    public Integer getSkinDiseases() { return skinDiseases; }
    public void setSkinDiseases(Integer skinDiseases) { this.skinDiseases = skinDiseases; }
    
    public Integer getMusculoskeletalDiseases() { return musculoskeletalDiseases; }
    public void setMusculoskeletalDiseases(Integer musculoskeletalDiseases) { this.musculoskeletalDiseases = musculoskeletalDiseases; }
    
    public Integer getGenitourinaryDiseases() { return genitourinaryDiseases; }
    public void setGenitourinaryDiseases(Integer genitourinaryDiseases) { this.genitourinaryDiseases = genitourinaryDiseases; }
    
    public Integer getPregnancyComplications() { return pregnancyComplications; }
    public void setPregnancyComplications(Integer pregnancyComplications) { this.pregnancyComplications = pregnancyComplications; }
    
    public Integer getPerinatalConditions() { return perinatalConditions; }
    public void setPerinatalConditions(Integer perinatalConditions) { this.perinatalConditions = perinatalConditions; }
    
    public Integer getCongenitalAnomalies() { return congenitalAnomalies; }
    public void setCongenitalAnomalies(Integer congenitalAnomalies) { this.congenitalAnomalies = congenitalAnomalies; }
    
    public Integer getInjuriesPoisoning() { return injuriesPoisoning; }
    public void setInjuriesPoisoning(Integer injuriesPoisoning) { this.injuriesPoisoning = injuriesPoisoning; }
    
    // Additional getters and setters for all other fields...
    // (Including specialist services, monthly statistics, medications, financial info, quality indicators)
    
    // Patient Statistics getters and setters
    public Integer getTotalPatients() { return totalPatients; }
    public void setTotalPatients(Integer totalPatients) { this.totalPatients = totalPatients; }
    
    public Integer getNewPatients() { return newPatients; }
    public void setNewPatients(Integer newPatients) { this.newPatients = newPatients; }
    
    public Integer getRepeatPatients() { return repeatPatients; }
    public void setRepeatPatients(Integer repeatPatients) { this.repeatPatients = repeatPatients; }
    
    // Hospital Operations getters and setters
    public Integer getBedsAvailable() { return bedsAvailable; }
    public void setBedsAvailable(Integer bedsAvailable) { this.bedsAvailable = bedsAvailable; }
    
    public Integer getCasesAdmitted() { return casesAdmitted; }
    public void setCasesAdmitted(Integer casesAdmitted) { this.casesAdmitted = casesAdmitted; }
    
    public Integer getCasesDischarged() { return casesDischarged; }
    public void setCasesDischarged(Integer casesDischarged) { this.casesDischarged = casesDischarged; }
    
    public Integer getTotalBedDays() { return totalBedDays; }
    public void setTotalBedDays(Integer totalBedDays) { this.totalBedDays = totalBedDays; }
    
    public java.math.BigDecimal getOccupancyPercentage() { return occupancyPercentage; }
    public void setOccupancyPercentage(java.math.BigDecimal occupancyPercentage) { this.occupancyPercentage = occupancyPercentage; }
    
    // TB Specific getters and setters
    public Integer getTbBeds() { return tbBeds; }
    public void setTbBeds(Integer tbBeds) { this.tbBeds = tbBeds; }
    
    public Integer getTbAdmitted() { return tbAdmitted; }
    public void setTbAdmitted(Integer tbAdmitted) { this.tbAdmitted = tbAdmitted; }
    
    public Integer getTbDischarged() { return tbDischarged; }
    public void setTbDischarged(Integer tbDischarged) { this.tbDischarged = tbDischarged; }
    
    // Maternity getters and setters
    public Integer getConfinements() { return confinements; }
    public void setConfinements(Integer confinements) { this.confinements = confinements; }
    
    public Integer getStillBirths() { return stillBirths; }
    public void setStillBirths(Integer stillBirths) { this.stillBirths = stillBirths; }
    
    public Integer getMaternalDeaths() { return maternalDeaths; }
    public void setMaternalDeaths(Integer maternalDeaths) { this.maternalDeaths = maternalDeaths; }
    
    public Integer getPerinatalDeaths() { return perinatalDeaths; }
    public void setPerinatalDeaths(Integer perinatalDeaths) { this.perinatalDeaths = perinatalDeaths; }
    
    // X-ray Services getters and setters
    public Integer getXraysDone() { return xraysDone; }
    public void setXraysDone(Integer xraysDone) { this.xraysDone = xraysDone; }
    
    // Specialist Consultations getters and setters
    public Integer getSpecialistConsultations() { return specialistConsultations; }
    public void setSpecialistConsultations(Integer specialistConsultations) { this.specialistConsultations = specialistConsultations; }

    // Summary Statistics getters and setters
    public Integer getTotalOpdCases() {
        return totalOpdCases;
    }

    public void setTotalOpdCases(Integer totalOpdCases) {
        this.totalOpdCases = totalOpdCases;
    }
    
    public Integer getTotalIpdCases() {
        return totalIpdCases;
    }

    public void setTotalIpdCases(Integer totalIpdCases) {
        this.totalIpdCases = totalIpdCases;
    }
    
    public Integer getTotalDiseaseCases() {
        return totalDiseaseCases;
    }

    public void setTotalDiseaseCases(Integer totalDiseaseCases) {
        this.totalDiseaseCases = totalDiseaseCases;
    }
    
    // Structured Data getters and setters
    public String getOpdData() {
        return opdData;
    }

    public void setOpdData(String opdData) {
        this.opdData = opdData;
    }
    
    public String getPathologyData() {
        return pathologyData;
    }

    public void setPathologyData(String pathologyData) {
        this.pathologyData = pathologyData;
    }
    
    public String getHospitalisationData() {
        return hospitalisationData;
    }

    public void setHospitalisationData(String hospitalisationData) {
        this.hospitalisationData = hospitalisationData;
    }

    public String getDiseaseData() { return diseaseData; }
    public void setDiseaseData(String diseaseData) { this.diseaseData = diseaseData; }
    
    // Missing getters and setters for fields used in LocationVisitService
    public Integer getLaboratoryTests() { return laboratoryTests; }
    public void setLaboratoryTests(Integer laboratoryTests) { this.laboratoryTests = laboratoryTests; }
    
    public Integer getSurgicalProcedures() { return surgicalProcedures; }
    public void setSurgicalProcedures(Integer surgicalProcedures) { this.surgicalProcedures = surgicalProcedures; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    // Additional specialist service getters and setters
    public Integer getCardiologyConsultations() { return cardiologyConsultations; }
    public void setCardiologyConsultations(Integer cardiologyConsultations) { this.cardiologyConsultations = cardiologyConsultations; }
    
    public Integer getNeurologyConsultations() { return neurologyConsultations; }
    public void setNeurologyConsultations(Integer neurologyConsultations) { this.neurologyConsultations = neurologyConsultations; }
    
    public Integer getOrthopedicConsultations() { return orthopedicConsultations; }
    public void setOrthopedicConsultations(Integer orthopedicConsultations) { this.orthopedicConsultations = orthopedicConsultations; }
    
    public Integer getPediatricConsultations() { return pediatricConsultations; }
    public void setPediatricConsultations(Integer pediatricConsultations) { this.pediatricConsultations = pediatricConsultations; }
    
    public Integer getGynecologyConsultations() { return gynecologyConsultations; }
    public void setGynecologyConsultations(Integer gynecologyConsultations) { this.gynecologyConsultations = gynecologyConsultations; }
    
    public Integer getDermatologyConsultations() { return dermatologyConsultations; }
    public void setDermatologyConsultations(Integer dermatologyConsultations) { this.dermatologyConsultations = dermatologyConsultations; }
    
    public Integer getPsychiatryConsultations() { return psychiatryConsultations; }
    public void setPsychiatryConsultations(Integer psychiatryConsultations) { this.psychiatryConsultations = psychiatryConsultations; }
    
    public Integer getOphthalmologyConsultations() { return ophthalmologyConsultations; }
    public void setOphthalmologyConsultations(Integer ophthalmologyConsultations) { this.ophthalmologyConsultations = ophthalmologyConsultations; }
    
    public Integer getEntConsultations() { return entConsultations; }
    public void setEntConsultations(Integer entConsultations) { this.entConsultations = entConsultations; }
    
    public Integer getRadiologyServices() { return radiologyServices; }
    public void setRadiologyServices(Integer radiologyServices) { this.radiologyServices = radiologyServices; }
    
    public Integer getPathologyServices() { return pathologyServices; }
    public void setPathologyServices(Integer pathologyServices) { this.pathologyServices = pathologyServices; }
    
    public Integer getPhysiotherapyServices() { return physiotherapyServices; }
    public void setPhysiotherapyServices(Integer physiotherapyServices) { this.physiotherapyServices = physiotherapyServices; }
    
    // Monthly statistics getters and setters
    public Integer getTotalOpPatients() { return totalOpPatients; }
    public void setTotalOpPatients(Integer totalOpPatients) { this.totalOpPatients = totalOpPatients; }
    
    public Integer getTotalIpPatients() { return totalIpPatients; }
    public void setTotalIpPatients(Integer totalIpPatients) { this.totalIpPatients = totalIpPatients; }
    
    public Integer getEmergencyCases() { return emergencyCases; }
    public void setEmergencyCases(Integer emergencyCases) { this.emergencyCases = emergencyCases; }
    
    public Integer getDiagnosticProcedures() { return diagnosticProcedures; }
    public void setDiagnosticProcedures(Integer diagnosticProcedures) { this.diagnosticProcedures = diagnosticProcedures; }
    
    public Integer getImagingStudies() { return imagingStudies; }
    public void setImagingStudies(Integer imagingStudies) { this.imagingStudies = imagingStudies; }
    
    // Medications getters and setters
    public Integer getAntibioticsPrescribed() { return antibioticsPrescribed; }
    public void setAntibioticsPrescribed(Integer antibioticsPrescribed) { this.antibioticsPrescribed = antibioticsPrescribed; }
    
    public Integer getAnalgesicsPrescribed() { return analgesicsPrescribed; }
    public void setAnalgesicsPrescribed(Integer analgesicsPrescribed) { this.analgesicsPrescribed = analgesicsPrescribed; }
    
    public Integer getVaccinesAdministered() { return vaccinesAdministered; }
    public void setVaccinesAdministered(Integer vaccinesAdministered) { this.vaccinesAdministered = vaccinesAdministered; }
    
    public Integer getSurgicalSuppliesUsed() { return surgicalSuppliesUsed; }
    public void setSurgicalSuppliesUsed(Integer surgicalSuppliesUsed) { this.surgicalSuppliesUsed = surgicalSuppliesUsed; }
    
    public Integer getMedicalDevicesUsed() { return medicalDevicesUsed; }
    public void setMedicalDevicesUsed(Integer medicalDevicesUsed) { this.medicalDevicesUsed = medicalDevicesUsed; }
    
    // Financial getters and setters
    public Double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }
    
    public Double getMedicineExpenditure() { return medicineExpenditure; }
    public void setMedicineExpenditure(Double medicineExpenditure) { this.medicineExpenditure = medicineExpenditure; }
    
    public Double getEquipmentExpenditure() { return equipmentExpenditure; }
    public void setEquipmentExpenditure(Double equipmentExpenditure) { this.equipmentExpenditure = equipmentExpenditure; }
    
    public Double getStaffSalaries() { return staffSalaries; }
    public void setStaffSalaries(Double staffSalaries) { this.staffSalaries = staffSalaries; }
    
    public Double getAdministrativeCosts() { return administrativeCosts; }
    public void setAdministrativeCosts(Double administrativeCosts) { this.administrativeCosts = administrativeCosts; }
    
    // Quality indicators getters and setters
    public Double getPatientSatisfactionScore() { return patientSatisfactionScore; }
    public void setPatientSatisfactionScore(Double patientSatisfactionScore) { this.patientSatisfactionScore = patientSatisfactionScore; }
    
    public Double getTreatmentSuccessRate() { return treatmentSuccessRate; }
    public void setTreatmentSuccessRate(Double treatmentSuccessRate) { this.treatmentSuccessRate = treatmentSuccessRate; }
    
    public Double getComplicationRate() { return complicationRate; }
    public void setComplicationRate(Double complicationRate) { this.complicationRate = complicationRate; }
    
    public Double getReadmissionRate() { return readmissionRate; }
    public void setReadmissionRate(Double readmissionRate) { this.readmissionRate = readmissionRate; }
    
    public Double getInfectionControlScore() { return infectionControlScore; }
    public void setInfectionControlScore(Double infectionControlScore) { this.infectionControlScore = infectionControlScore; }
    
    // Additional information getters and setters
    public String getSpecialCampaigns() { return specialCampaigns; }
    public void setSpecialCampaigns(String specialCampaigns) { this.specialCampaigns = specialCampaigns; }
    
    public String getResearchActivities() { return researchActivities; }
    public void setResearchActivities(String researchActivities) { this.researchActivities = researchActivities; }
    
    public String getTrainingPrograms() { return trainingPrograms; }
    public void setTrainingPrograms(String trainingPrograms) { this.trainingPrograms = trainingPrograms; }
    
    public String getChallengesFaced() { return challengesFaced; }
    public void setChallengesFaced(String challengesFaced) { this.challengesFaced = challengesFaced; }
    
    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }
    
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
