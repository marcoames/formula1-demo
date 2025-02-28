package com.example.formula1_demo.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.formula1_demo.entity.Reserva;
import com.example.formula1_demo.entity.User;
import com.example.formula1_demo.repository.ReservaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> listarTodasReservas() {
        return reservaRepository.findAll();
    }

    public Reserva createReserva() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User authenticatedUser = (User) authentication.getPrincipal();

        Reserva reserva = new Reserva();
        reserva.setUsuario(authenticatedUser);
        reserva.setDataCheckIn(null);
        reserva.setDataCheckOut(null);
        
        return reservaRepository.save(reserva);
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
