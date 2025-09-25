package com.example.demo.service;

import com.example.demo.entity.EsiMed6A;
import com.example.demo.repository.EsiMed6ARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EsiMed6AService {
    
    @Autowired
    private EsiMed6ARepository esiMed6ARepository;
    
    public EsiMed6A save(EsiMed6A esiMed6A) {
        System.out.println("💾 Saving ESI Med-6A form");
        return esiMed6ARepository.save(esiMed6A);
    }
    
    public Optional<EsiMed6A> findById(Long id) {
        return esiMed6ARepository.findById(id);
    }
    
    public List<EsiMed6A> findByOfficerId(Long officerId) {
        return esiMed6ARepository.findByOfficerId(officerId);
    }
    
    public List<EsiMed6A> findByLocation(String location) {
        return esiMed6ARepository.findByLocation(location);
    }
    
    public void deleteById(Long id) {
        esiMed6ARepository.deleteById(id);
    }
    
    public List<EsiMed6A> getAllForms() {
        return esiMed6ARepository.findAll();
    }
}
