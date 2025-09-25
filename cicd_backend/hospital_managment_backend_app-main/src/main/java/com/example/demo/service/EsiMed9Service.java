package com.example.demo.service;

import com.example.demo.entity.EsiMed9;
import com.example.demo.repository.EsiMed9Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EsiMed9Service {
    
    @Autowired
    private EsiMed9Repository esiMed9Repository;
    
    public EsiMed9 save(EsiMed9 esiMed9) {
        System.out.println("💾 Saving ESI Med-9 form");
        return esiMed9Repository.save(esiMed9);
    }
    
    public List<EsiMed9> findAll() {
        return esiMed9Repository.findAll();
    }
    
    public Optional<EsiMed9> findById(Long id) {
        return esiMed9Repository.findById(id);
    }
    
    public List<EsiMed9> findByOfficerId(Long officerId) {
        return esiMed9Repository.findByOfficerId(officerId);
    }
    
    public List<EsiMed9> findByLocation(String location) {
        return esiMed9Repository.findByLocation(location);
    }
    
    public void deleteById(Long id) {
        esiMed9Repository.deleteById(id);
    }
    
    public List<EsiMed9> getAllForms() {
        return esiMed9Repository.findAll();
    }
}
