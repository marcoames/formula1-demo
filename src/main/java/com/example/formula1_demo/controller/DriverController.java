package com.example.formula1_demo.controller;

import com.example.formula1_demo.DTO.ErrorResponse;
import com.example.formula1_demo.entity.Driver;
import com.example.formula1_demo.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;
    
    @GetMapping("")
    public ResponseEntity<List<Driver>> getDrivers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String nationality,
            @RequestParam(required = false) String seasons,
            @RequestParam(required = false) Boolean champion) {

        if (name != null && nationality != null) {
            return driverService.getDriversByNameAndNationality(name, nationality)
                    .map(drivers -> new ResponseEntity<>(drivers, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else if (name != null) {
            return driverService.getDriverByName(name)
                    .map(drivers -> new ResponseEntity<>(drivers, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else if (nationality != null) {
            return driverService.getDriverByNationality(nationality)
                    .map(drivers -> new ResponseEntity<>(drivers, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else if (seasons != null) {
            return driverService.getDriverBySeasonsActive(seasons)
                    .map(drivers -> new ResponseEntity<>(drivers, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else if (champion != null) {
            return driverService.getDriverByChampion(champion)
                    .map(drivers -> new ResponseEntity<>(drivers, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            List<Driver> drivers = driverService.getAllDrivers();
            return new ResponseEntity<>(drivers, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id)
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Driver> createDriver(@RequestBody final Driver driver) {
        Driver savedDriver = driverService.createDriver(driver);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        Driver updatedDriver = driverService.updateDriver(id, driver);
        return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long id) {
        try {
            driverService.deleteDriver(id);
            return new ResponseEntity<>("Driver with id {id} deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        
    }
}
