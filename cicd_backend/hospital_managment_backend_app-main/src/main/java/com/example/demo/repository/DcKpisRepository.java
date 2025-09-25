package com.example.demo.repository;

import com.example.demo.entity.DcKpis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DcKpisRepository extends JpaRepository<DcKpis, Long> {
    
    List<DcKpis> findByOfficerIdAndLocation(Long officerId, String location);
    Optional<DcKpis> findByOfficerIdAndLocationAndMonth(Long officerId, String location, String month);
    List<DcKpis> findByOfficerId(Long officerId);
    List<DcKpis> findByLocation(String location);
    List<DcKpis> findByMonth(String month);
    List<DcKpis> findByStatus(String status);
    List<DcKpis> findByOfficerIdAndStatus(Long officerId, String status);
    
    @Query("SELECT d FROM DcKpis d WHERE d.officerId = :officerId AND d.month >= :startMonth AND d.month <= :endMonth ORDER BY d.month DESC")
    List<DcKpis> findByOfficerIdAndMonthRange(@Param("officerId") Long officerId, 
                                              @Param("startMonth") String startMonth, 
                                              @Param("endMonth") String endMonth);
    
    boolean existsByOfficerIdAndLocationAndMonth(Long officerId, String location, String month);
}
