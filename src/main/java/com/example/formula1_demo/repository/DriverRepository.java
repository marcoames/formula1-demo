package com.example.formula1_demo.repository;

import com.example.formula1_demo.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}