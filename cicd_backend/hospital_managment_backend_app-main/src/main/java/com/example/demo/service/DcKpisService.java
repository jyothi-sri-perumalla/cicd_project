package com.example.demo.service;

import com.example.demo.dto.DcKpisRequest;
import com.example.demo.entity.DcKpis;
import com.example.demo.repository.DcKpisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DcKpisService {
    
    @Autowired
    private DcKpisRepository dcKpisRepository;
    
    public DcKpis createOrUpdateForm(DcKpisRequest request) {
        // Check if form already exists for this officer, location, and month
        Optional<DcKpis> existingForm = dcKpisRepository.findByOfficerIdAndLocationAndMonth(
            request.getOfficerId(), request.getLocation(), request.getMonth());
        
        DcKpis form;
        if (existingForm.isPresent()) {
            // Update existing form
            form = existingForm.get();
            updateFormFromRequest(form, request);
        } else {
            // Create new form
            form = new DcKpis(request.getOfficerId(), request.getLocation(), request.getMonth());
            updateFormFromRequest(form, request);
        }
        
        return dcKpisRepository.save(form);
    }
    
    public DcKpis submitForm(Long formId) {
        Optional<DcKpis> formOpt = dcKpisRepository.findById(formId);
        if (formOpt.isPresent()) {
            DcKpis form = formOpt.get();
            form.setStatus("SUBMITTED");
            form.setSubmittedAt(LocalDateTime.now());
            return dcKpisRepository.save(form);
        }
        throw new RuntimeException("Form not found with ID: " + formId);
    }
    
    public List<DcKpis> getFormsByOfficerAndLocation(Long officerId, String location) {
        return dcKpisRepository.findByOfficerIdAndLocation(officerId, location);
    }
    
    public List<DcKpis> getFormsByOfficer(Long officerId) {
        return dcKpisRepository.findByOfficerId(officerId);
    }
    
    public Optional<DcKpis> getFormByOfficerLocationAndMonth(Long officerId, String location, String month) {
        return dcKpisRepository.findByOfficerIdAndLocationAndMonth(officerId, location, month);
    }
    
    public List<DcKpis> getFormsByLocation(String location) {
        return dcKpisRepository.findByLocation(location);
    }
    
    public List<DcKpis> getFormsByStatus(String status) {
        return dcKpisRepository.findByStatus(status);
    }
    
    public DcKpis updateFormStatus(Long formId, String status) {
        Optional<DcKpis> formOpt = dcKpisRepository.findById(formId);
        if (formOpt.isPresent()) {
            DcKpis form = formOpt.get();
            form.setStatus(status);
            if ("SUBMITTED".equals(status)) {
                form.setSubmittedAt(LocalDateTime.now());
            }
            return dcKpisRepository.save(form);
        }
        throw new RuntimeException("Form not found with ID: " + formId);
    }
    
    public boolean deleteForm(Long formId) {
        if (dcKpisRepository.existsById(formId)) {
            dcKpisRepository.deleteById(formId);
            return true;
        }
        return false;
    }
    
    private void updateFormFromRequest(DcKpis form, DcKpisRequest request) {
        form.setImmunization(request.getImmunization());
        form.setSterilization(request.getSterilization());
        form.setOpTreatment(request.getOpTreatment());
        form.setIpTreatment(request.getIpTreatment());
        form.setBedOccupancy(request.getBedOccupancy());
        form.setLabInvestigations(request.getLabInvestigations());
        form.setHealthCamps(request.getHealthCamps());
    }
    
    // Admin method to get all DC KPIs forms
    public List<DcKpis> getAllForms() {
        return dcKpisRepository.findAll();
    }
}
