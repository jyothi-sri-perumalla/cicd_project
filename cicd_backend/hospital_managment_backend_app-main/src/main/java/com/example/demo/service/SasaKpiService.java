package com.example.demo.service;

import com.example.demo.entity.SasaKpi;
import com.example.demo.repository.SasaKpiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SasaKpiService {
    
    @Autowired
    private SasaKpiRepository sasaKpiRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    public SasaKpi saveSasaKpi(Long officerId, String location, Map<String, Object> formData) {
        try {
            // Extract checklist data
            Object checklistDataObj = formData.get("checklist");
            String checklistDataJson = objectMapper.writeValueAsString(checklistDataObj);
            
            // Get current month in YYYY-MM format
            String currentMonth = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
            
            // Always create a new assessment (allow multiple submissions per month)
            SasaKpi sasaKpi = new SasaKpi(officerId, location, currentMonth);
            sasaKpi.setChecklistData(checklistDataJson);
            
            // Calculate submission sequence for this officer and month
            List<SasaKpi> existingAssessments = sasaKpiRepository.findByOfficerIdAndMonth(officerId, currentMonth);
            int nextSequence = existingAssessments.size() + 1;
            sasaKpi.setSubmissionSequence(nextSequence);
            
            System.out.println("📋 Creating SASA KPI assessment #" + nextSequence + " for officer " + officerId + " in month " + currentMonth);
            
            // Extract and store individual indicator responses
            extractIndicatorResponses(sasaKpi, checklistDataObj);
            
            // Calculate summary statistics
            calculateSummaryStatistics(sasaKpi, checklistDataObj);
            
            // Set submission timestamp and status
            sasaKpi.setSubmittedAt(LocalDateTime.now());
            sasaKpi.setStatus("SUBMITTED");
            
            SasaKpi savedAssessment = sasaKpiRepository.save(sasaKpi);
            System.out.println("✅ New SASA KPI assessment created with ID: " + savedAssessment.getId());
            
            return savedAssessment;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save SASA KPI assessment: " + e.getMessage());
        }
    }
    
    private void extractIndicatorResponses(SasaKpi sasaKpi, Object checklistDataObj) {
        try {
            JsonNode checklistNode = objectMapper.valueToTree(checklistDataObj);
            
            if (checklistNode.isArray()) {
                for (JsonNode item : checklistNode) {
                    int id = getIntValue(item, "id");
                    String response = getStringValue(item, "response");
                    
                    // Map responses to specific fields based on indicator ID
                    switch (id) {
                        case 1: // Biomedical waste disposal
                            sasaKpi.setBiomedicalWasteDisposal(response);
                            break;
                        case 2: // Toilet maintenance
                            sasaKpi.setToiletMaintenance(response);
                            break;
                        case 3: // Safe drinking water
                            sasaKpi.setSafeDrinkingWater(response);
                            break;
                        case 4: // Sanitation worker training
                            sasaKpi.setSanitationWorkerTraining(response);
                            break;
                        case 5: // Staff hygiene training
                            sasaKpi.setStaffHygieneTraining(response);
                            break;
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Error extracting indicator responses: " + e.getMessage());
        }
    }
    
    private void calculateSummaryStatistics(SasaKpi sasaKpi, Object checklistDataObj) {
        try {
            JsonNode checklistNode = objectMapper.valueToTree(checklistDataObj);
            
            if (checklistNode.isArray()) {
                int totalIndicators = 0;
                int yesResponses = 0;
                int noResponses = 0;
                
                for (JsonNode item : checklistNode) {
                    String response = getStringValue(item, "response");
                    if (!response.isEmpty()) {
                        totalIndicators++;
                        if ("Yes".equalsIgnoreCase(response)) {
                            yesResponses++;
                        } else if ("No".equalsIgnoreCase(response)) {
                            noResponses++;
                        }
                    }
                }
                
                // Calculate compliance percentage
                double compliancePercentage = totalIndicators > 0 ? 
                    (double) yesResponses / totalIndicators * 100.0 : 0.0;
                
                // Set summary statistics
                sasaKpi.setTotalIndicators(totalIndicators);
                sasaKpi.setYesResponses(yesResponses);
                sasaKpi.setNoResponses(noResponses);
                sasaKpi.setCompliancePercentage(compliancePercentage);
                
                System.out.println("📊 SASA KPI Summary: " + yesResponses + "/" + totalIndicators + 
                                 " indicators compliant (" + String.format("%.1f", compliancePercentage) + "%)");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            // Set default values if calculation fails
            sasaKpi.setTotalIndicators(0);
            sasaKpi.setYesResponses(0);
            sasaKpi.setNoResponses(0);
            sasaKpi.setCompliancePercentage(0.0);
        }
    }
    
    private int getIntValue(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        if (fieldNode != null && !fieldNode.isNull()) {
            if (fieldNode.isNumber()) {
                return fieldNode.asInt();
            } else if (fieldNode.isTextual()) {
                try {
                    return Integer.parseInt(fieldNode.asText());
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }
        return 0;
    }
    
    private String getStringValue(JsonNode node, String fieldName) {
        JsonNode fieldNode = node.get(fieldName);
        if (fieldNode != null && !fieldNode.isNull()) {
            return fieldNode.asText();
        }
        return "";
    }
    
    // Additional service methods
    public List<SasaKpi> getAssessmentsByOfficer(Long officerId) {
        return sasaKpiRepository.findByOfficerId(officerId);
    }
    
    public List<SasaKpi> getAssessmentsByLocation(String location) {
        return sasaKpiRepository.findByLocation(location);
    }
    
    public List<SasaKpi> getAssessmentsByOfficerAndMonth(Long officerId, String month) {
        return sasaKpiRepository.findByOfficerIdAndMonth(officerId, month);
    }
    
    public List<SasaKpi> getAssessmentsByStatus(String status) {
        return sasaKpiRepository.findByStatus(status);
    }
    
    public Optional<SasaKpi> getAssessmentById(Long id) {
        return sasaKpiRepository.findById(id);
    }
    
    public void deleteAssessment(Long id) {
        sasaKpiRepository.deleteById(id);
    }
    
    public SasaKpi updateAssessmentStatus(Long id, String status) {
        Optional<SasaKpi> assessmentOpt = sasaKpiRepository.findById(id);
        if (assessmentOpt.isPresent()) {
            SasaKpi assessment = assessmentOpt.get();
            assessment.setStatus(status);
            assessment.setUpdatedAt(LocalDateTime.now());
            return sasaKpiRepository.save(assessment);
        }
        throw new RuntimeException("SASA KPI assessment not found with id: " + id);
    }
    
    public List<SasaKpi> getAllAssessments() {
        return sasaKpiRepository.findAll();
    }
    
    public List<SasaKpi> getAllForms() {
        return sasaKpiRepository.findAll();
    }
    
    public long countAssessmentsByOfficer(Long officerId) {
        return sasaKpiRepository.countByOfficerId(officerId);
    }
    
    public boolean assessmentExistsForOfficerAndMonth(Long officerId, String month) {
        return sasaKpiRepository.existsByOfficerIdAndMonth(officerId, month);
    }
}
