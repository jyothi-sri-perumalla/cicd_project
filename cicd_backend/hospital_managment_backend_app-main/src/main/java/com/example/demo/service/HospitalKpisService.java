package com.example.demo.service;

import com.example.demo.entity.HospitalKpis;
import com.example.demo.repository.HospitalKpisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalKpisService {
    
    @Autowired
    private HospitalKpisRepository hospitalKpisRepository;
    
    public HospitalKpis createOrUpdateForm(HospitalKpis request) {
        // Check if form already exists for this officer, location, and month
        Optional<HospitalKpis> existingForm = hospitalKpisRepository.findByOfficerIdAndLocationAndMonth(
            request.getOfficerId(), request.getLocation(), request.getMonth());
        
        HospitalKpis form;
        if (existingForm.isPresent()) {
            // Update existing form
            form = existingForm.get();
            updateFormFromRequest(form, request);
        } else {
            // Create new form
            form = new HospitalKpis(request.getOfficerId(), request.getLocation(), request.getMonth());
            updateFormFromRequest(form, request);
        }
        
        return hospitalKpisRepository.save(form);
    }
    
    public HospitalKpis submitForm(Long formId) {
        Optional<HospitalKpis> formOpt = hospitalKpisRepository.findById(formId);
        if (formOpt.isPresent()) {
            HospitalKpis form = formOpt.get();
            form.setStatus("SUBMITTED");
            form.setSubmittedAt(LocalDateTime.now());
            return hospitalKpisRepository.save(form);
        }
        throw new RuntimeException("Form not found with ID: " + formId);
    }
    
    public List<HospitalKpis> getFormsByOfficerAndLocation(Long officerId, String location) {
        return hospitalKpisRepository.findByOfficerIdAndLocation(officerId, location);
    }
    
    public List<HospitalKpis> getFormsByOfficer(Long officerId) {
        return hospitalKpisRepository.findByOfficerId(officerId);
    }
    
    public Optional<HospitalKpis> getFormByOfficerLocationAndMonth(Long officerId, String location, String month) {
        return hospitalKpisRepository.findByOfficerIdAndLocationAndMonth(officerId, location, month);
    }
    
    public List<HospitalKpis> getFormsByLocation(String location) {
        return hospitalKpisRepository.findByLocation(location);
    }
    
    public List<HospitalKpis> getFormsByStatus(String status) {
        return hospitalKpisRepository.findByStatus(status);
    }
    
    public HospitalKpis updateFormStatus(Long formId, String status) {
        Optional<HospitalKpis> formOpt = hospitalKpisRepository.findById(formId);
        if (formOpt.isPresent()) {
            HospitalKpis form = formOpt.get();
            form.setStatus(status);
            if ("SUBMITTED".equals(status)) {
                form.setSubmittedAt(LocalDateTime.now());
            }
            return hospitalKpisRepository.save(form);
        }
        throw new RuntimeException("Form not found with ID: " + formId);
    }
    
    public boolean deleteForm(Long formId) {
        if (hospitalKpisRepository.existsById(formId)) {
            hospitalKpisRepository.deleteById(formId);
            return true;
        }
        return false;
    }
    
    private void updateFormFromRequest(HospitalKpis form, HospitalKpis request) {
        // Copy all fields from request to form
        form.setPolioDoses(request.getPolioDoses());
        form.setDptDoses(request.getDptDoses());
        form.setBcgDoses(request.getBcgDoses());
        form.setMeaslesDoses(request.getMeaslesDoses());
        form.setHepatitisDoses(request.getHepatitisDoses());
        form.setTetanusDoses(request.getTetanusDoses());
        form.setCovidDoses(request.getCovidDoses());
        form.setTotalImmunization(request.getTotalImmunization());
        
        // Add all other field mappings as needed...
        // This is a simplified version - you would need to add all fields
    }
    
    // Admin method to get all Hospital KPIs forms
    public List<HospitalKpis> getAllForms() {
        return hospitalKpisRepository.findAll();
    }
}
