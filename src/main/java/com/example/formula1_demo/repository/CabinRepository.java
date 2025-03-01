package com.example.formula1_demo.repository;

import com.example.formula1_demo.entity.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CabinRepository extends JpaRepository<Cabin, Long> {
    
}
