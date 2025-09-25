package com.example.demo.repository;

import com.example.demo.entity.KpiReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface KpiReportRepository extends JpaRepository<KpiReport, Long> {
    
    // Find by officer ID
    List<KpiReport> findByOfficerId(Long officerId);
    
    // Find by location
    List<KpiReport> findByLocation(String location);
    
    // Find by month (YYYY-MM format)
    List<KpiReport> findByMonth(String month);
    
    // Find by officer and month (returns list since multiple submissions allowed)
    List<KpiReport> findByOfficerIdAndMonth(Long officerId, String month);
    
    // Find by officer and location
    List<KpiReport> findByOfficerIdAndLocation(Long officerId, String location);
    
    // Find by status
    List<KpiReport> findByStatus(String status);
    
    // Find by officer and status
    List<KpiReport> findByOfficerIdAndStatus(Long officerId, String status);
    
    // Find reports submitted within date range
    @Query("SELECT k FROM KpiReport k WHERE k.submittedAt BETWEEN :startDate AND :endDate")
    List<KpiReport> findBySubmittedAtBetween(@Param("startDate") LocalDateTime startDate, 
                                           @Param("endDate") LocalDateTime endDate);
    
    // Find latest report by officer
    @Query("SELECT k FROM KpiReport k WHERE k.officerId = :officerId ORDER BY k.submittedAt DESC")
    List<KpiReport> findLatestByOfficerId(@Param("officerId") Long officerId);
    
    // Count total reports by officer
    long countByOfficerId(Long officerId);
    
    // Count reports by status
    long countByStatus(String status);
    
    // Find reports with summary statistics
    @Query("SELECT k FROM KpiReport k WHERE k.totalDispensaries > :minDispensaries")
    List<KpiReport> findReportsWithMinDispensaries(@Param("minDispensaries") Integer minDispensaries);
    
    // Check if report exists for officer and month
    boolean existsByOfficerIdAndMonth(Long officerId, String month);
}
