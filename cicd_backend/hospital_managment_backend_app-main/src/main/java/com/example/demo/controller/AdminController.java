package com.example.demo.controller;

import com.example.demo.dto.AdminLoginRequest;
import com.example.demo.dto.AdminLoginResponse;
import com.example.demo.dto.AdminRegistrationRequest;
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {
    "https://hospital-managment-frontned-app.vercel.app/",
    "https://hospital-managment-frontned-app.vercel.app/",
    "http://localhost:3000"
}, methods = {
    RequestMethod.GET,
    RequestMethod.POST,
    RequestMethod.PUT,
    RequestMethod.DELETE,
    RequestMethod.OPTIONS
}, allowedHeaders = "*", allowCredentials = "true")
public class AdminController {

  @Autowired
  private AdminService adminService;

  @PostMapping("/register")
  public ResponseEntity<AdminLoginResponse> registerAdmin(@Valid @RequestBody AdminRegistrationRequest request) {
    AdminLoginResponse response = adminService.registerAdmin(request);

    if (response.getToken() != null) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.badRequest().body(response);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<AdminLoginResponse> loginAdmin(@Valid @RequestBody AdminLoginRequest request) {
    AdminLoginResponse response = adminService.loginAdmin(request);

    if (response.getToken() != null) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
  }

  @GetMapping("/profile")
  public ResponseEntity<Map<String, Object>> getAdminProfile(Authentication authentication) {
    String email = authentication.getName();
    Optional<Admin> adminOpt = adminService.findByEmail(email);

    if (adminOpt.isPresent()) {
      Admin admin = adminOpt.get();
      Map<String, Object> profile = new HashMap<>();
      profile.put("id", admin.getId());
      profile.put("email", admin.getEmail());
      profile.put("fullName", admin.getFullName());
      profile.put("isActive", admin.getIsActive());
      profile.put("createdAt", admin.getCreatedAt());
      profile.put("lastLogin", admin.getLastLogin());

      return ResponseEntity.ok(profile);
    } else {
      Map<String, Object> error = new HashMap<>();
      error.put("message", "Admin not found");
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<Map<String, String>> logout() {
    // For JWT, logout is handled client-side by removing the token
    Map<String, String> response = new HashMap<>();
    response.put("message", "Logged out successfully");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/health")
  public ResponseEntity<Map<String, String>> healthCheck() {
    Map<String, String> response = new HashMap<>();
    response.put("status", "OK");
    response.put("message", "Admin service is running");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/verify-token")
  public ResponseEntity<Map<String, Object>> verifyToken(Authentication authentication) {
    String email = authentication.getName();
    Optional<Admin> adminOpt = adminService.findByEmail(email);

    if (adminOpt.isPresent()) {
      Admin admin = adminOpt.get();
      Map<String, Object> response = new HashMap<>();
      response.put("valid", true);
      response.put("email", admin.getEmail());
      response.put("fullName", admin.getFullName());
      response.put("adminId", admin.getId());

      return ResponseEntity.ok(response);
    } else {
      Map<String, Object> response = new HashMap<>();
      response.put("valid", false);
      response.put("message", "Invalid token");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
  }
}
