package com.example.formula1_demo.controller;

import com.example.formula1_demo.entity.Driver;
import com.example.formula1_demo.service.DriverService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // Driver Endpoints
    @GetMapping("/driver")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Driver>> getDrivers(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String nationality
    ) {

        if (name != null) {
            return driverService.getDriverByName(name)
                    .map(drivers -> new ResponseEntity<>(drivers, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        else if (nationality != null) {
            return driverService.getDriverByNationality(nationality)
                    .map(drivers -> new ResponseEntity<>(drivers, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        else {
            List<Driver> drivers = driverService.getAllDrivers();
            return new ResponseEntity<>(drivers, HttpStatus.OK);
        }
        
    }

    @GetMapping("/driver/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id)
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/driver")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Driver> createDriver(@RequestBody final Driver driver) {
        Driver savedDriver = driverService.createDriver(driver);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }

    @PutMapping("/driver/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        Driver updatedDriver = driverService.updateDriver(id, driver);
        return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
    }


    @DeleteMapping("/driver/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}