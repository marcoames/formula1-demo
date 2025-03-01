package com.example.formula1_demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.formula1_demo.entity.Cabin;
import com.example.formula1_demo.service.CabinService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/cabins")
public class CabinController {

    private final CabinService cabinService;

    public CabinController(CabinService cabinService) {
        this.cabinService = cabinService;
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public List<Cabin> listarCabins() {
        return cabinService.listarTodasCabins();
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCabin(@RequestBody Cabin cabin) {
        cabinService.createCabin(cabin);
        return new ResponseEntity<>("Cabin criada", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cabin> getCabinById(@PathVariable Long id) {
        return cabinService.getCabinById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
}
