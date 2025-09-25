package com.example.demo.repository;

import com.example.demo.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfficerRepository extends JpaRepository<Officer, Long> {
    
    Optional<Officer> findByEmail(String email);
    
    Optional<Officer> findByMobileNumber(String mobileNumber);
    
    List<Officer> findByIsActiveTrue();
    
    @Query("SELECT o FROM Officer o JOIN o.locations l WHERE l = :location AND o.isActive = true")
    List<Officer> findByLocation(@Param("location") String location);
    
    @Query("SELECT o FROM Officer o JOIN o.locations l WHERE l IN :locations AND o.isActive = true")
    List<Officer> findByLocationsIn(@Param("locations") List<String> locations);
    
    boolean existsByEmail(String email);
    
    boolean existsByMobileNumber(String mobileNumber);
}
