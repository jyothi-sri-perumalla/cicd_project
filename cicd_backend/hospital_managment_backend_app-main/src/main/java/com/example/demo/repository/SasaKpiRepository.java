package com.example.demo.repository;

import com.example.demo.entity.SasaKpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SasaKpiRepository extends JpaRepository<SasaKpi, Long> {
    
    // Find by officer ID
    List<SasaKpi> findByOfficerId(Long officerId);
    
    // Find by location
    List<SasaKpi> findByLocation(String location);
    
    // Find by month (YYYY-MM format)
    List<SasaKpi> findByMonth(String month);
    
    // Find by officer and month (returns list since multiple submissions allowed)
    List<SasaKpi> findByOfficerIdAndMonth(Long officerId, String month);
    
    // Find by officer and location
    List<SasaKpi> findByOfficerIdAndLocation(Long officerId, String location);
    
    // Find by status
    List<SasaKpi> findByStatus(String status);
    
    // Find by officer and status
    List<SasaKpi> findByOfficerIdAndStatus(Long officerId, String status);
    
    // Find assessments submitted within date range
    @Query("SELECT s FROM SasaKpi s WHERE s.submittedAt BETWEEN :startDate AND :endDate")
    List<SasaKpi> findBySubmittedAtBetween(@Param("startDate") LocalDateTime startDate, 
                                          @Param("endDate") LocalDateTime endDate);
    
    // Find latest assessment by officer
    @Query("SELECT s FROM SasaKpi s WHERE s.officerId = :officerId ORDER BY s.submittedAt DESC")
    List<SasaKpi> findLatestByOfficerId(@Param("officerId") Long officerId);
    
    // Count total assessments by officer
    long countByOfficerId(Long officerId);
    
    // Count assessments by status
    long countByStatus(String status);
    
    // Find assessments with specific compliance percentage
    @Query("SELECT s FROM SasaKpi s WHERE s.compliancePercentage >= :minCompliance")
    List<SasaKpi> findAssessmentsWithMinCompliance(@Param("minCompliance") Double minCompliance);
    
    // Check if assessment exists for officer and month
    boolean existsByOfficerIdAndMonth(Long officerId, String month);
}
