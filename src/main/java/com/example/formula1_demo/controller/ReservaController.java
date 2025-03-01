package com.example.formula1_demo.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.formula1_demo.DTO.ErrorResponse;
import com.example.formula1_demo.DTO.ReservationRequestDTO;
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
    public ResponseEntity<?> createReserva(@RequestBody ReservationRequestDTO reservationRequest) {
        try { 
            reservaService.createReserva(reservationRequest);
            return new ResponseEntity<>("Reserva criada", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        return reservaService.getReservaById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{usuarioId}")
    public ResponseEntity<?> getReservasByUsuario(@PathVariable Long usuarioId) {
        List<Reserva> reservas = reservaService.getReservasByUsuarioId(usuarioId);
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }
    
    
}
