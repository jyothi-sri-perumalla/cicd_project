package com.example.demo.repository;

import com.example.demo.entity.LocationVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationVisitRepository extends JpaRepository<LocationVisit, Long> {
    
    // Find all visits by officer
    List<LocationVisit> findByOfficerIdOrderByCreatedAtDesc(Long officerId);
    
    // Find visits by officer and location
    List<LocationVisit> findByOfficerIdAndLocationOrderByCreatedAtDesc(Long officerId, String location);
    
    // Find visits by officer, location and form type
    List<LocationVisit> findByOfficerIdAndLocationAndFormTypeOrderByCreatedAtDesc(Long officerId, String location, String formType);
    
    // Find latest visit by officer and location
    @Query("SELECT v FROM LocationVisit v WHERE v.officerId = :officerId AND v.location = :location ORDER BY v.createdAt DESC")
    Optional<LocationVisit> findLatestVisitByOfficerAndLocation(@Param("officerId") Long officerId, @Param("location") String location);
    
    // Check if officer has submitted forms for a location
    @Query("SELECT COUNT(v) > 0 FROM LocationVisit v WHERE v.officerId = :officerId AND v.location = :location AND v.status = 'SUBMITTED'")
    boolean hasSubmittedFormsForLocation(@Param("officerId") Long officerId, @Param("location") String location);
    
    // Get all locations visited by officer
    @Query("SELECT DISTINCT v.location FROM LocationVisit v WHERE v.officerId = :officerId")
    List<String> findDistinctLocationsByOfficerId(@Param("officerId") Long officerId);
    
    // Find visits by status
    List<LocationVisit> findByStatusOrderByCreatedAtDesc(String status);
}
