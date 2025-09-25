package com.example.demo.service;

import com.example.demo.entity.KpiReport;
import com.example.demo.repository.KpiReportRepository;
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
public class KpiReportService {
    
    @Autowired
    private KpiReportRepository kpiReportRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    public KpiReport saveKpiReport(Long officerId, String location, Map<String, Object> formData) {
        try {
            // Extract report data and calculate summary statistics
            Object reportDataObj = formData.get("reportData");
            String reportDataJson = objectMapper.writeValueAsString(reportDataObj);
            
            // Get current month in YYYY-MM format
            String currentMonth = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
            
            // Always create a new report (remove the duplicate check to allow multiple submissions)
            KpiReport kpiReport = new KpiReport(officerId, location, currentMonth);
            kpiReport.setReportData(reportDataJson);
            
            // Calculate submission sequence for this officer and month
            List<KpiReport> existingReports = kpiReportRepository.findByOfficerIdAndMonth(officerId, currentMonth);
            int nextSequence = existingReports.size() + 1;
            kpiReport.setSubmissionSequence(nextSequence);
            
            System.out.println("📊 Creating KPI Report submission #" + nextSequence + " for officer " + officerId + " in month " + currentMonth);
            
            // Calculate summary statistics from report data
            calculateSummaryStatistics(kpiReport, reportDataObj);
            
            // Set submission timestamp and status
            kpiReport.setSubmittedAt(LocalDateTime.now());
            kpiReport.setStatus("SUBMITTED");
            
            KpiReport savedReport = kpiReportRepository.save(kpiReport);
            System.out.println("✅ New KPI Report created with ID: " + savedReport.getId());
            
            return savedReport;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save KPI Report: " + e.getMessage());
        }
    }
    
    private void calculateSummaryStatistics(KpiReport kpiReport, Object reportDataObj) {
        try {
            JsonNode reportDataNode = objectMapper.valueToTree(reportDataObj);
            
            if (reportDataNode.isArray()) {
                int totalDispensaries = 0;
                int totalIpsAttached = 0;
                int totalOnlineRegistrations = 0;
                int totalOfflineRegistrations = 0;
                int totalAvgDailyOpd = 0;
                int totalSterilization = 0;
                int totalImmunization = 0;
                int totalLabInvestigations = 0;
                
                for (JsonNode dispensary : reportDataNode) {
                    totalDispensaries++;
                    
                    // Sum up all the KPI metrics
                    totalIpsAttached += getIntValue(dispensary, "ipsAttached");
                    totalOnlineRegistrations += getIntValue(dispensary, "onlineOpdRegistrations");
                    totalOfflineRegistrations += getIntValue(dispensary, "offlineOpdRegistrations");
                    totalAvgDailyOpd += getIntValue(dispensary, "avgDailyOpd");
                    totalSterilization += getIntValue(dispensary, "sterilization");
                    totalImmunization += getIntValue(dispensary, "immunization");
                    totalLabInvestigations += getIntValue(dispensary, "labInvestigations");
                }
                
                // Set summary statistics
                kpiReport.setTotalDispensaries(totalDispensaries);
                kpiReport.setTotalIpsAttached(totalIpsAttached);
                kpiReport.setTotalOnlineRegistrations(totalOnlineRegistrations);
                kpiReport.setTotalOfflineRegistrations(totalOfflineRegistrations);
                kpiReport.setTotalRegistrations(totalOnlineRegistrations + totalOfflineRegistrations);
                kpiReport.setTotalAvgDailyOpd(totalAvgDailyOpd);
                kpiReport.setTotalSterilization(totalSterilization);
                kpiReport.setTotalImmunization(totalImmunization);
                kpiReport.setTotalLabInvestigations(totalLabInvestigations);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            // Set default values if calculation fails
            kpiReport.setTotalDispensaries(0);
            kpiReport.setTotalIpsAttached(0);
            kpiReport.setTotalOnlineRegistrations(0);
            kpiReport.setTotalOfflineRegistrations(0);
            kpiReport.setTotalRegistrations(0);
            kpiReport.setTotalAvgDailyOpd(0);
            kpiReport.setTotalSterilization(0);
            kpiReport.setTotalImmunization(0);
            kpiReport.setTotalLabInvestigations(0);
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
    
    // Additional service methods
    public List<KpiReport> getReportsByOfficer(Long officerId) {
        return kpiReportRepository.findByOfficerId(officerId);
    }
    
    public List<KpiReport> getReportsByLocation(String location) {
        return kpiReportRepository.findByLocation(location);
    }
    
    public Optional<KpiReport> getReportByOfficerAndMonth(Long officerId, String month) {
        List<KpiReport> reports = kpiReportRepository.findByOfficerIdAndMonth(officerId, month);
        return reports.isEmpty() ? Optional.empty() : Optional.of(reports.get(reports.size() - 1)); // Return latest
    }
    
    public List<KpiReport> getAllReportsByOfficerAndMonth(Long officerId, String month) {
        return kpiReportRepository.findByOfficerIdAndMonth(officerId, month);
    }
    
    public List<KpiReport> getReportsByStatus(String status) {
        return kpiReportRepository.findByStatus(status);
    }
    
    public Optional<KpiReport> getReportById(Long id) {
        return kpiReportRepository.findById(id);
    }
    
    public void deleteReport(Long id) {
        kpiReportRepository.deleteById(id);
    }
    
    public KpiReport updateReportStatus(Long id, String status) {
        Optional<KpiReport> reportOpt = kpiReportRepository.findById(id);
        if (reportOpt.isPresent()) {
            KpiReport report = reportOpt.get();
            report.setStatus(status);
            report.setUpdatedAt(LocalDateTime.now());
            return kpiReportRepository.save(report);
        }
        throw new RuntimeException("KPI Report not found with id: " + id);
    }
    
    public List<KpiReport> getAllReports() {
        return kpiReportRepository.findAll();
    }
    
    public List<KpiReport> getAllForms() {
        return kpiReportRepository.findAll();
    }
    
    public long countReportsByOfficer(Long officerId) {
        return kpiReportRepository.countByOfficerId(officerId);
    }
    
    public boolean reportExistsForOfficerAndMonth(Long officerId, String month) {
        return kpiReportRepository.existsByOfficerIdAndMonth(officerId, month);
    }
}
