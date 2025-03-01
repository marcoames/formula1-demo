package com.example.formula1_demo.service;

import org.springframework.stereotype.Service;

import com.example.formula1_demo.entity.Cabin;
import com.example.formula1_demo.repository.CabinRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CabinService {
    
    private final CabinRepository cabinRepository;

    public CabinService(CabinRepository cabinRepository) {
        this.cabinRepository = cabinRepository;
    }

    public List<Cabin> listarTodasCabins() {
        return cabinRepository.findAll();
    }

    public Cabin createCabin(Cabin cabin) {
        return cabinRepository.save(cabin);
    }

    public Optional<Cabin> getCabinById(Long id) {
        return cabinRepository.findById(id);
    }
        

}
