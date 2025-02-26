package com.example.formula1_demo.service;

import com.example.formula1_demo.entity.Driver;
import com.example.formula1_demo.repository.DriverRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    public Optional<List<Driver>> getDriverByNationality(String nationalString) {
        return Optional.ofNullable(driverRepository.findByNationality(nationalString));
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Long id, Driver driver) {
        return driverRepository.findById(id)
                .map(existingDriver -> {
                    existingDriver.setDriver(driver.getDriver());
                    existingDriver.setNationality(driver.getNationality());
                    existingDriver.setSeasons(driver.getSeasons());
                    existingDriver.setChampionships(driver.getChampionships());
                    existingDriver.setRaceEntries(driver.getRaceEntries());
                    existingDriver.setRaceStarts(driver.getRaceStarts());
                    existingDriver.setPolePositions(driver.getPolePositions());
                    existingDriver.setRaceWins(driver.getRaceWins());
                    existingDriver.setPodiums(driver.getPodiums());
                    existingDriver.setFastestLaps(driver.getFastestLaps());
                    existingDriver.setPoints(driver.getPoints());
                    existingDriver.setActive(driver.getActive());
                    existingDriver.setChampionshipYears(driver.getChampionshipYears());
                    existingDriver.setDecade(driver.getDecade());
                    existingDriver.setPoleRate(driver.getPoleRate());
                    existingDriver.setStartRate(driver.getStartRate());
                    existingDriver.setWinRate(driver.getWinRate());
                    existingDriver.setPodiumRate(driver.getPodiumRate());
                    existingDriver.setFastLapRate(driver.getFastLapRate());
                    existingDriver.setPointsPerEntry(driver.getPointsPerEntry());
                    existingDriver.setYearsActive(driver.getYearsActive());
                    existingDriver.setChampion(driver.getChampion());
                    return driverRepository.save(existingDriver);
                })
                .orElseGet(() -> {
                    driver.setId(id);
                    return driverRepository.save(driver);
                });
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}