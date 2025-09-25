package com.example.demo.service;

import com.example.demo.dto.OfficerRegistrationRequest;
import com.example.demo.dto.OfficerResponse;
import com.example.demo.entity.Officer;
import com.example.demo.repository.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OfficerService {
    
    @Autowired
    private OfficerRepository officerRepository;
    
    @Autowired
    private OtpService otpService;
    
    @Autowired
    private PasswordService passwordService;
    
    public OfficerService() {
        System.out.println("🛠️ OfficerService created!");
    }
    
    public OfficerResponse registerOfficer(OfficerRegistrationRequest request) {
        System.out.println("📋 OfficerService.registerOfficer called with: " + request.toString());
        // Check if email already exists
        if (officerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        
        // Check if mobile number already exists
        if (officerRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new RuntimeException("Mobile number already registered");
        }
        
        // Validate mobile number format
        if (!isValidMobileNumber(request.getMobileNumber())) {
            throw new RuntimeException("Invalid mobile number format");
        }
        
        // Filter out empty locations
        List<String> validLocations = request.getLocations().stream()
                .filter(location -> location != null && !location.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.toList());
        
        if (validLocations.isEmpty()) {
            throw new RuntimeException("At least one location is required");
        }
        
        // Create new officer
        Officer officer = new Officer(
            request.getFullName().trim(),
            request.getEmail().toLowerCase().trim(),
            request.getMobileNumber(),
            validLocations
        );
        
        Officer savedOfficer = officerRepository.save(officer);
        
        return convertToResponse(savedOfficer);
    }
    
    public List<OfficerResponse> getAllActiveOfficers() {
        return officerRepository.findByIsActiveTrue()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public Optional<OfficerResponse> getOfficerById(Long id) {
        return officerRepository.findById(id)
                .map(this::convertToResponse);
    }
    
    public List<OfficerResponse> getOfficersByLocation(String location) {
        return officerRepository.findByLocation(location)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public List<OfficerResponse> getOfficersByLocations(List<String> locations) {
        return officerRepository.findByLocationsIn(locations)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public OfficerResponse updateOfficer(Long id, OfficerRegistrationRequest request) {
        Officer officer = officerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Officer not found"));
        
        // Check if email is being changed and if it already exists
        if (!officer.getEmail().equals(request.getEmail()) && 
            officerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        
        // Check if mobile number is being changed and if it already exists
        if (!officer.getMobileNumber().equals(request.getMobileNumber()) && 
            officerRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new RuntimeException("Mobile number already registered");
        }
        
        // Filter out empty locations
        List<String> validLocations = request.getLocations().stream()
                .filter(location -> location != null && !location.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.toList());
        
        if (validLocations.isEmpty()) {
            throw new RuntimeException("At least one location is required");
        }
        
        // Update officer details
        officer.setFullName(request.getFullName().trim());
        officer.setEmail(request.getEmail().toLowerCase().trim());
        officer.setMobileNumber(request.getMobileNumber());
        officer.setLocations(validLocations);
        
        Officer updatedOfficer = officerRepository.save(officer);
        return convertToResponse(updatedOfficer);
    }
    
    public void deactivateOfficer(Long id) {
        Officer officer = officerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Officer not found"));
        
        officer.setIsActive(false);
        officerRepository.save(officer);
    }
    
    public void activateOfficer(Long id) {
        Officer officer = officerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Officer not found"));
        
        officer.setIsActive(true);
        officerRepository.save(officer);
    }
    
    // Officer Login Methods
    public boolean checkOfficerExists(String mobileNumber) {
        // Format mobile number to match database format
        String formattedNumber = formatMobileNumber(mobileNumber);
        return officerRepository.existsByMobileNumber(formattedNumber);
    }
    
    public boolean isPasswordSet(String mobileNumber) {
        String formattedNumber = formatMobileNumber(mobileNumber);
        Optional<Officer> officer = officerRepository.findByMobileNumber(formattedNumber);
        return officer.isPresent() && Boolean.TRUE.equals(officer.get().getPasswordSet());
    }
    
    public boolean setOfficerPassword(String mobileNumber, String password, String retypePassword) {
        System.out.println("🔐 Setting password for officer: " + mobileNumber);
        
        // Validate passwords match
        if (!password.equals(retypePassword)) {
            throw new RuntimeException("Passwords do not match");
        }
        
        // Validate password strength
        if (!passwordService.isValidPassword(password)) {
            throw new RuntimeException(passwordService.getPasswordRequirements());
        }
        
        String formattedNumber = formatMobileNumber(mobileNumber);
        
        // Check if officer exists
        Officer officer = officerRepository.findByMobileNumber(formattedNumber)
                .orElseThrow(() -> new RuntimeException("Officer not found with this mobile number"));
        
        if (!officer.getIsActive()) {
            throw new RuntimeException("Officer account is deactivated");
        }
        
        // Hash and set password
        String hashedPassword = passwordService.hashPassword(password);
        officer.setPasswordHash(hashedPassword);
        officer.setPasswordSet(true);
        
        officerRepository.save(officer);
        
        System.out.println("✅ Password set successfully for officer: " + mobileNumber);
        return true;
    }
    
    public OfficerResponse loginWithPassword(String mobileNumber, String password) {
        System.out.println("🔓 Attempting login for officer: " + mobileNumber);
        
        String formattedNumber = formatMobileNumber(mobileNumber);
        
        // Get officer details
        Officer officer = officerRepository.findByMobileNumber(formattedNumber)
                .orElseThrow(() -> new RuntimeException("Officer not found with this mobile number"));
        
        if (!officer.getIsActive()) {
            throw new RuntimeException("Officer account is deactivated");
        }
        
        if (!Boolean.TRUE.equals(officer.getPasswordSet()) || officer.getPasswordHash() == null) {
            throw new RuntimeException("Password not set. Please set your password first.");
        }
        
        // Verify password
        if (!passwordService.verifyPassword(password, officer.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }
        
        System.out.println("✅ Officer login successful: " + officer.getFullName());
        return convertToResponse(officer);
    }
    
    private String formatMobileNumber(String mobileNumber) {
        // Ensure mobile number is in +91XXXXXXXXXX format
        if (mobileNumber == null) return null;
        
        String cleaned = mobileNumber.replaceAll("[\\s\\-\\(\\)]", "");
        
        if (cleaned.startsWith("+91")) {
            return cleaned;
        } else if (cleaned.startsWith("91") && cleaned.length() == 12) {
            return "+" + cleaned;
        } else if (cleaned.length() == 10) {
            return "+91" + cleaned;
        }
        
        return cleaned;
    }
    
    private boolean isValidMobileNumber(String mobileNumber) {
        // Check if mobile number starts with +91 and has 10 digits after
        if (mobileNumber == null) return false;
        
        String pattern = "^\\+91[6-9]\\d{9}$";
        return mobileNumber.matches(pattern);
    }
    
    private OfficerResponse convertToResponse(Officer officer) {
        return new OfficerResponse(
            officer.getId(),
            officer.getFullName(),
            officer.getEmail(),
            officer.getMobileNumber(),
            officer.getLocations(),
            officer.getCreatedAt(),
            officer.getIsActive()
        );
    }
}
