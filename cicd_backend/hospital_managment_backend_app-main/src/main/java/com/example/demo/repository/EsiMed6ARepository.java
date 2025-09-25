package com.example.demo.repository;

import com.example.demo.entity.EsiMed6A;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EsiMed6ARepository extends JpaRepository<EsiMed6A, Long> {
    
    List<EsiMed6A> findByOfficerIdAndLocation(Long officerId, String location);
    Optional<EsiMed6A> findByOfficerIdAndLocationAndMonth(Long officerId, String location, String month);
    List<EsiMed6A> findByOfficerId(Long officerId);
    List<EsiMed6A> findByLocation(String location);
    List<EsiMed6A> findByMonth(String month);
    List<EsiMed6A> findByStatus(String status);
    List<EsiMed6A> findByOfficerIdAndStatus(Long officerId, String status);
    
    @Query("SELECT e FROM EsiMed6A e WHERE e.officerId = :officerId AND e.month >= :startMonth AND e.month <= :endMonth ORDER BY e.month DESC")
    List<EsiMed6A> findByOfficerIdAndMonthRange(@Param("officerId") Long officerId, 
                                                @Param("startMonth") String startMonth, 
                                                @Param("endMonth") String endMonth);
    
    boolean existsByOfficerIdAndLocationAndMonth(Long officerId, String location, String month);
}
