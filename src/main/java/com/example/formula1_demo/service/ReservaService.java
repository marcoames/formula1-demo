package com.example.formula1_demo.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.formula1_demo.DTO.ReservationRequestDTO;
import com.example.formula1_demo.entity.Cabin;
import com.example.formula1_demo.entity.Reserva;
import com.example.formula1_demo.entity.User;
import com.example.formula1_demo.repository.CabinRepository;
import com.example.formula1_demo.repository.ReservaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    
    private final ReservaRepository reservaRepository;
    private final CabinRepository cabinRepository;

    public ReservaService(ReservaRepository reservaRepository, CabinRepository cabinRepository) {
        this.reservaRepository = reservaRepository;
        this.cabinRepository = cabinRepository;
    }

    public List<Reserva> listarTodasReservas() {
        return reservaRepository.findAll();
    }

    public Reserva createReserva(ReservationRequestDTO reservationRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User authenticatedUser = (User) authentication.getPrincipal();
        Cabin cabin = cabinRepository.findById(reservationRequest.cabinId()).orElseThrow(() -> new RuntimeException("Cabin not found")); ;
        
        Reserva newReserva = new Reserva();

        newReserva.setUsuario(authenticatedUser);
        newReserva.setCabin(cabin);
        newReserva.setDataCheckIn(reservationRequest.checkIn());
        newReserva.setDataCheckOut(reservationRequest.checkOut());
        
        return reservaRepository.save(newReserva);
    }

    public Optional<Reserva> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }
        

    public List<Reserva> getReservasByUsuarioId(Long usuarioId) {
    return reservaRepository.findAll().stream()
        .filter(reserva -> reserva.getUsuario().getId().equals(usuarioId))
        .collect(Collectors.toList()); 
    }







}
