package com.example.demo.repository;

import com.example.demo.entity.EsiMed6;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EsiMed6Repository extends JpaRepository<EsiMed6, Long> {
    
    // Find by officer and location
    List<EsiMed6> findByOfficerIdAndLocation(Long officerId, String location);
    
    // Find by officer, location and month
    Optional<EsiMed6> findByOfficerIdAndLocationAndMonth(Long officerId, String location, String month);
    
    // Find by officer
    List<EsiMed6> findByOfficerId(Long officerId);
    
    // Find by location
    List<EsiMed6> findByLocation(String location);
    
    // Find by month
    List<EsiMed6> findByMonth(String month);
    
    // Find by status
    List<EsiMed6> findByStatus(String status);
    
    // Find by officer and status
    List<EsiMed6> findByOfficerIdAndStatus(Long officerId, String status);
    
    // Find by location and status
    List<EsiMed6> findByLocationAndStatus(String location, String status);
    
    // Find by month and status
    List<EsiMed6> findByMonthAndStatus(String month, String status);
    
    // Custom query to get all forms for a specific officer within a date range
    @Query("SELECT e FROM EsiMed6 e WHERE e.officerId = :officerId AND e.month >= :startMonth AND e.month <= :endMonth ORDER BY e.month DESC")
    List<EsiMed6> findByOfficerIdAndMonthRange(@Param("officerId") Long officerId, 
                                               @Param("startMonth") String startMonth, 
                                               @Param("endMonth") String endMonth);
    
    // Custom query to get summary statistics
    @Query("SELECT COUNT(e) FROM EsiMed6 e WHERE e.location = :location AND e.month = :month")
    Long countByLocationAndMonth(@Param("location") String location, @Param("month") String month);
    
    // Get latest submission for officer and location
    @Query("SELECT e FROM EsiMed6 e WHERE e.officerId = :officerId AND e.location = :location ORDER BY e.createdAt DESC")
    List<EsiMed6> findLatestByOfficerAndLocation(@Param("officerId") Long officerId, @Param("location") String location);
    
    // Check if form exists for officer, location and month
    boolean existsByOfficerIdAndLocationAndMonth(Long officerId, String location, String month);
}
