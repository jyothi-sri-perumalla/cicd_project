package com.example.demo.repository;

import com.example.demo.entity.HospitalKpis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalKpisRepository extends JpaRepository<HospitalKpis, Long> {
    
    List<HospitalKpis> findByOfficerIdAndLocation(Long officerId, String location);
    Optional<HospitalKpis> findByOfficerIdAndLocationAndMonth(Long officerId, String location, String month);
    List<HospitalKpis> findByOfficerId(Long officerId);
    List<HospitalKpis> findByLocation(String location);
    List<HospitalKpis> findByMonth(String month);
    List<HospitalKpis> findByStatus(String status);
    List<HospitalKpis> findByOfficerIdAndStatus(Long officerId, String status);
    
    @Query("SELECT h FROM HospitalKpis h WHERE h.officerId = :officerId AND h.month >= :startMonth AND h.month <= :endMonth ORDER BY h.month DESC")
    List<HospitalKpis> findByOfficerIdAndMonthRange(@Param("officerId") Long officerId, 
                                                    @Param("startMonth") String startMonth, 
                                                    @Param("endMonth") String endMonth);
    
    boolean existsByOfficerIdAndLocationAndMonth(Long officerId, String location, String month);
}
