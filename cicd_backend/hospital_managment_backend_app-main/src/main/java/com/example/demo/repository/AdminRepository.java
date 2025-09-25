package com.example.demo.repository;

import com.example.demo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

  Optional<Admin> findByEmail(String email);

  boolean existsByEmail(String email);

  Optional<Admin> findByEmailAndIsActive(String email, Boolean isActive);

  @Modifying
  @Query("UPDATE Admin a SET a.lastLogin = :lastLogin WHERE a.id = :id")
  void updateLastLogin(Long id, LocalDateTime lastLogin);

  @Modifying
  @Query("UPDATE Admin a SET a.isActive = :isActive WHERE a.id = :id")
  void updateActiveStatus(Long id, Boolean isActive);
}
