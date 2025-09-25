package com.example.demo.service;

import com.example.demo.dto.AdminLoginRequest;
import com.example.demo.dto.AdminLoginResponse;
import com.example.demo.dto.AdminRegistrationRequest;
import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtUtil jwtUtil;

  public AdminLoginResponse registerAdmin(AdminRegistrationRequest request) {
    try {
      // Check if admin already exists
      if (adminRepository.existsByEmail(request.getEmail())) {
        return new AdminLoginResponse("Admin with this email already exists");
      }

      // Create new admin
      Admin admin = new Admin();
      admin.setEmail(request.getEmail());
      admin.setPassword(passwordEncoder.encode(request.getPassword()));
      admin.setFullName(request.getFullName());
      admin.setIsActive(true);

      Admin savedAdmin = adminRepository.save(admin);

      // Generate token
      String token = jwtUtil.generateToken(savedAdmin.getEmail(), savedAdmin.getId());

      return new AdminLoginResponse(
          token,
          savedAdmin.getId(),
          savedAdmin.getEmail(),
          savedAdmin.getFullName(),
          savedAdmin.getCreatedAt());

    } catch (Exception e) {
      return new AdminLoginResponse("Registration failed: " + e.getMessage());
    }
  }

  public AdminLoginResponse loginAdmin(AdminLoginRequest request) {
    try {
      // Find admin by email
      Optional<Admin> optionalAdmin = adminRepository.findByEmailAndIsActive(request.getEmail(), true);

      if (optionalAdmin.isEmpty()) {
        return new AdminLoginResponse("Invalid email or password");
      }

      Admin admin = optionalAdmin.get();

      // Check password
      if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
        return new AdminLoginResponse("Invalid email or password");
      }

      // Update last login
      admin.setLastLogin(LocalDateTime.now());
      adminRepository.save(admin);

      // Generate token
      String token = jwtUtil.generateToken(admin.getEmail(), admin.getId());

      return new AdminLoginResponse(
          token,
          admin.getId(),
          admin.getEmail(),
          admin.getFullName(),
          admin.getLastLogin());

    } catch (Exception e) {
      return new AdminLoginResponse("Login failed: " + e.getMessage());
    }
  }

  public Optional<Admin> findByEmail(String email) {
    return adminRepository.findByEmailAndIsActive(email, true);
  }

  public Optional<Admin> findById(Long id) {
    return adminRepository.findById(id);
  }
}
