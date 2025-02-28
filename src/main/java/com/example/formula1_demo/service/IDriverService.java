package com.example.formula1_demo.service;

import com.example.formula1_demo.entity.Driver;

import java.util.List;
import java.util.Optional;

public interface IDriverService {
    List<Driver> getAllDrivers();
    Optional<Driver> getDriverById(Long id);
    Optional<List<Driver>> getDriverByName(String name);
    Optional<List<Driver>> getDriverByNationality(String nationality);
    Optional<List<Driver>> getDriversByNameAndNationality(String name, String nationality);
    Optional<List<Driver>> getDriverBySeasonsActive(String seasons);
    Optional<List<Driver>> getDriverByChampion(Boolean champion);
    Driver createDriver(Driver driver);
    Driver updateDriver(Long id, Driver driver);
    void deleteDriver(Long id);
}
