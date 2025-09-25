package com.example.demo.service;

import com.example.demo.dto.EsiMed6Request;
import com.example.demo.dto.EsiMed6Response;
import com.example.demo.entity.EsiMed6;
import com.example.demo.repository.EsiMed6Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EsiMed6Service {
    
    @Autowired
    private EsiMed6Repository esiMed6Repository;
    
    public EsiMed6 save(EsiMed6 esiMed6) {
        esiMed6.setSubmittedAt(LocalDateTime.now());
        esiMed6.setStatus("SUBMITTED");
        return esiMed6Repository.save(esiMed6);
    }
    
    public EsiMed6Response createOrUpdateForm(EsiMed6Request request) {
        // Check if form already exists for this officer, location, and month
        Optional<EsiMed6> existingForm = esiMed6Repository.findByOfficerIdAndLocationAndMonth(
            request.getOfficerId(), request.getLocation(), request.getMonth());
        
        EsiMed6 form;
        if (existingForm.isPresent()) {
            // Update existing form
            form = existingForm.get();
            updateFormFromRequest(form, request);
        } else {
            // Create new form
            form = new EsiMed6(request.getOfficerId(), request.getLocation(), request.getMonth());
            updateFormFromRequest(form, request);
        }
        
        EsiMed6 savedForm = esiMed6Repository.save(form);
        return convertToResponse(savedForm);
    }
    
    public EsiMed6Response submitForm(Long formId) {
        Optional<EsiMed6> formOpt = esiMed6Repository.findById(formId);
        if (formOpt.isPresent()) {
            EsiMed6 form = formOpt.get();
            form.setStatus("SUBMITTED");
            form.setSubmittedAt(LocalDateTime.now());
            EsiMed6 savedForm = esiMed6Repository.save(form);
            return convertToResponse(savedForm);
        }
        throw new RuntimeException("Form not found with ID: " + formId);
    }
    
    public List<EsiMed6Response> getFormsByOfficerAndLocation(Long officerId, String location) {
        List<EsiMed6> forms = esiMed6Repository.findByOfficerIdAndLocation(officerId, location);
        return forms.stream().map(this::convertToResponse).collect(Collectors.toList());
    }
    
    public List<EsiMed6Response> getFormsByOfficer(Long officerId) {
        List<EsiMed6> forms = esiMed6Repository.findByOfficerId(officerId);
        return forms.stream().map(this::convertToResponse).collect(Collectors.toList());
    }
    
    public Optional<EsiMed6Response> getFormByOfficerLocationAndMonth(Long officerId, String location, String month) {
        Optional<EsiMed6> form = esiMed6Repository.findByOfficerIdAndLocationAndMonth(officerId, location, month);
        return form.map(this::convertToResponse);
    }
    
    public List<EsiMed6Response> getFormsByLocation(String location) {
        List<EsiMed6> forms = esiMed6Repository.findByLocation(location);
        return forms.stream().map(this::convertToResponse).collect(Collectors.toList());
    }
    
    public List<EsiMed6Response> getFormsByStatus(String status) {
        List<EsiMed6> forms = esiMed6Repository.findByStatus(status);
        return forms.stream().map(this::convertToResponse).collect(Collectors.toList());
    }
    
    public EsiMed6Response updateFormStatus(Long formId, String status) {
        Optional<EsiMed6> formOpt = esiMed6Repository.findById(formId);
        if (formOpt.isPresent()) {
            EsiMed6 form = formOpt.get();
            form.setStatus(status);
            if ("SUBMITTED".equals(status)) {
                form.setSubmittedAt(LocalDateTime.now());
            }
            EsiMed6 savedForm = esiMed6Repository.save(form);
            return convertToResponse(savedForm);
        }
        throw new RuntimeException("Form not found with ID: " + formId);
    }
    
    public boolean deleteForm(Long formId) {
        if (esiMed6Repository.existsById(formId)) {
            esiMed6Repository.deleteById(formId);
            return true;
        }
        return false;
    }
    
    private void updateFormFromRequest(EsiMed6 form, EsiMed6Request request) {
        // Institution Information
        form.setInstitutionName(request.getInstitutionName());
        form.setInstitutionCode(request.getInstitutionCode());
        form.setRegion(request.getRegion());
        
        // Treatment Summary
        form.setOpNewCases(request.getOpNewCases());
        form.setOpOldCases(request.getOpOldCases());
        form.setIpNewCases(request.getIpNewCases());
        form.setIpOldCases(request.getIpOldCases());
        form.setTotalTreatments(request.getTotalTreatments());
        
        // Medical Services
        form.setSpecialistConsultations(request.getSpecialistConsultations());
        form.setEmergencyCases(request.getEmergencyCases());
        form.setSurgicalProcedures(request.getSurgicalProcedures());
        form.setDiagnosticTests(request.getDiagnosticTests());
        form.setPhysiotherapySessions(request.getPhysiotherapySessions());
        
        // Medications and Supplies
        form.setMedicinesDispensed(request.getMedicinesDispensed());
        form.setMedicalSuppliesUsed(request.getMedicalSuppliesUsed());
        
        // Financial Information
        form.setTotalExpenditure(request.getTotalExpenditure());
        form.setMedicineCost(request.getMedicineCost());
        form.setEquipmentCost(request.getEquipmentCost());
        
        // Additional Notes
        form.setRemarks(request.getRemarks());
    }
    
    private EsiMed6Response convertToResponse(EsiMed6 form) {
        EsiMed6Response response = new EsiMed6Response();
        
        response.setId(form.getId());
        response.setOfficerId(form.getOfficerId());
        response.setLocation(form.getLocation());
        response.setMonth(form.getMonth());
        
        // Institution Information
        response.setInstitutionName(form.getInstitutionName());
        response.setInstitutionCode(form.getInstitutionCode());
        response.setRegion(form.getRegion());
        
        // Treatment Summary
        response.setOpNewCases(form.getOpNewCases());
        response.setOpOldCases(form.getOpOldCases());
        response.setIpNewCases(form.getIpNewCases());
        response.setIpOldCases(form.getIpOldCases());
        response.setTotalTreatments(form.getTotalTreatments());
        
        // Medical Services
        response.setSpecialistConsultations(form.getSpecialistConsultations());
        response.setEmergencyCases(form.getEmergencyCases());
        response.setSurgicalProcedures(form.getSurgicalProcedures());
        response.setDiagnosticTests(form.getDiagnosticTests());
        response.setPhysiotherapySessions(form.getPhysiotherapySessions());
        
        // Medications and Supplies
        response.setMedicinesDispensed(form.getMedicinesDispensed());
        response.setMedicalSuppliesUsed(form.getMedicalSuppliesUsed());
        
        // Financial Information
        response.setTotalExpenditure(form.getTotalExpenditure());
        response.setMedicineCost(form.getMedicineCost());
        response.setEquipmentCost(form.getEquipmentCost());
        
        // Additional Information
        response.setRemarks(form.getRemarks());
        response.setStatus(form.getStatus());
        response.setSubmittedAt(form.getSubmittedAt());
        response.setCreatedAt(form.getCreatedAt());
        response.setUpdatedAt(form.getUpdatedAt());
        
        return response;
    }
    
    // Admin method to get all ESI MED6 forms
    public List<EsiMed6Response> getAllForms() {
        List<EsiMed6> forms = esiMed6Repository.findAll();
        return forms.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
}
