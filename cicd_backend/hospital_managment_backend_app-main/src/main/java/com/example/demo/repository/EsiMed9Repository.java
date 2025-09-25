package com.example.demo.repository;

import com.example.demo.entity.EsiMed9;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EsiMed9Repository extends JpaRepository<EsiMed9, Long> {
    
    List<EsiMed9> findByOfficerIdAndLocation(Long officerId, String location);
    Optional<EsiMed9> findByOfficerIdAndLocationAndMonth(Long officerId, String location, String month);
    List<EsiMed9> findByOfficerId(Long officerId);
    List<EsiMed9> findByLocation(String location);
    List<EsiMed9> findByMonth(String month);
    List<EsiMed9> findByStatus(String status);
    List<EsiMed9> findByOfficerIdAndStatus(Long officerId, String status);
    
    @Query("SELECT e FROM EsiMed9 e WHERE e.officerId = :officerId AND e.month >= :startMonth AND e.month <= :endMonth ORDER BY e.month DESC")
    List<EsiMed9> findByOfficerIdAndMonthRange(@Param("officerId") Long officerId, 
                                               @Param("startMonth") String startMonth, 
                                               @Param("endMonth") String endMonth);
    
    boolean existsByOfficerIdAndLocationAndMonth(Long officerId, String location, String month);
}
