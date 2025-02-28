package com.example.formula1_demo.service;

import com.example.formula1_demo.entity.Driver;
import com.example.formula1_demo.repository.DriverRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DriverService implements IDriverService {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public Optional<List<Driver>> getDriverByName(String name) {
        List<Driver> drivers = driverRepository.findAll().stream()
                .filter(driver -> driver.getDriver().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        return Optional.of(drivers);
    }

    @Override
    public Optional<List<Driver>> getDriverByNationality(String nationality) {
        List<Driver> drivers = driverRepository.findAll().stream()
                .filter(driver -> driver.getNationality().equalsIgnoreCase(nationality))
                .collect(Collectors.toList());
        return Optional.of(drivers);                
    }

    @Override
    public Optional<List<Driver>> getDriversByNameAndNationality(String name, String nationality) {
        List<Driver> drivers = driverRepository.findAll().stream()
                .filter(driver -> driver.getDriver().toLowerCase().contains(name.toLowerCase()) &&
                                  driver.getNationality().equalsIgnoreCase(nationality))
                .collect(Collectors.toList());
        return Optional.of(drivers);
    }

    @Override
    public Optional<List<Driver>> getDriverBySeasonsActive(String seasons) {
        List<Driver> drivers = driverRepository.findAll().stream()
                .filter(driver -> driver.getSeasons().toLowerCase().contains(seasons.toLowerCase()))
                .collect(Collectors.toList());
        return Optional.of(drivers);
    }

    @Override
    public Optional<List<Driver>> getDriverByChampion(Boolean champion) {
        List<Driver> drivers = driverRepository.findAll().stream()
                .filter(driver -> driver.getChampion().equals(champion))
                .collect(Collectors.toList());
        return Optional.of(drivers);
    }

    @Override
    public Driver createDriver(Driver driver) {
        
        return driverRepository.save(driver);
    }

    @Override
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

    @Override
    public void deleteDriver(Long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (driver.isPresent()) {
            driverRepository.deleteById(id);
        } else {
            throw new RuntimeException("Driver with ID " + id + " not found.");
        }
    }

}
