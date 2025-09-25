package com.example.demo.controller;

import com.example.demo.dto.OfficerRegistrationRequest;
import com.example.demo.dto.OfficerResponse;
import com.example.demo.dto.LocationVisitRequest;
import com.example.demo.dto.LocationVisitResponse;
import com.example.demo.service.OfficerService;
import com.example.demo.service.OtpService;
import com.example.demo.service.LocationVisitService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/officers")
@CrossOrigin(origins = {
        "https://hospital-managment-frontned-app.vercel.app",
        "http://localhost:3000"
}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowedHeaders = "*", allowCredentials = "true")
public class OfficerController {

    @Autowired
    private OfficerService officerService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private LocationVisitService locationVisitService;

    @Autowired
    private JwtUtil jwtUtil;

    public OfficerController() {
        System.out.println("🏗️ OfficerController created!");
    }

    // ================== TEST ENDPOINT ==================
    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Officer controller is working!");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    // ================== OFFICER REGISTRATION ==================
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> registerOfficer(@RequestBody OfficerRegistrationRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (request.getFullName() == null || request.getFullName().trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Full name is required");
                return ResponseEntity.badRequest().body(response);
            }
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Email is required");
                return ResponseEntity.badRequest().body(response);
            }
            if (request.getMobileNumber() == null || request.getMobileNumber().trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "Mobile number is required");
                return ResponseEntity.badRequest().body(response);
            }
            if (request.getLocations() == null || request.getLocations().isEmpty()) {
                response.put("success", false);
                response.put("message", "At least one location is required");
                return ResponseEntity.badRequest().body(response);
            }

            OfficerResponse officer = officerService.registerOfficer(request);
            response.put("success", true);
            response.put("message", "Officer registered successfully");
            response.put("officer", officer);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ================== GET ALL OFFICERS ==================
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getAllOfficers() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<OfficerResponse> officers = officerService.getAllActiveOfficers();
            response.put("success", true);
            response.put("officers", officers);
            response.put("count", officers.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ================== GET OFFICER BY ID ==================
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> getOfficerById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<OfficerResponse> officer = officerService.getOfficerById(id);
            if (officer.isPresent()) {
                response.put("success", true);
                response.put("officer", officer.get());
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Officer not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ================== DEACTIVATE / ACTIVATE OFFICER ==================
    @PatchMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> deactivateOfficer(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            officerService.deactivateOfficer(id);
            response.put("success", true);
            response.put("message", "Officer deactivated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> activateOfficer(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            officerService.activateOfficer(id);
            response.put("success", true);
            response.put("message", "Officer activated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ================== GLOBAL SUGGESTION ==================
    // Consider adding a @ControllerAdvice class for centralized exception handling
}
