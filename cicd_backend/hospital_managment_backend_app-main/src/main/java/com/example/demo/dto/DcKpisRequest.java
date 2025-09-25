package com.example.demo.dto;

public class DcKpisRequest {
    
    private Long officerId;
    private String location;
    private String month;
    
    // DC KPI Data
    private Integer immunization;
    private Integer sterilization;
    private Integer opTreatment;
    private Integer ipTreatment;
    private Integer bedOccupancy;
    private Integer labInvestigations;
    private Integer healthCamps;
    
    // Constructors
    public DcKpisRequest() {}
    
    // Getters and Setters
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
}
