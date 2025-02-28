package com.example.formula1_demo.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.formula1_demo.entity.Reserva;
import com.example.formula1_demo.service.ReservaService;

import java.util.List;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Reserva> listarReservas() {
        return reservaService.listarTodasReservas();
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createReserva() {
        reservaService.createReserva();
        return new ResponseEntity<>("Reserva criada", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        return reservaService.getReservaById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{usuarioId}")
    public List<Reserva> getReservasByUsuario(@PathVariable Long usuarioId) {
        return reservaService.getReservasByUsuarioId(usuarioId);
    }



    
    
}
