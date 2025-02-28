package com.example.formula1_demo.repository;

import com.example.formula1_demo.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // List<Reserva> findByCabanaIdAndDataCheckInBetween(, LocalDate start, LocalDate end);
}
