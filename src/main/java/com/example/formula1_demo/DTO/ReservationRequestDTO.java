package com.example.formula1_demo.DTO;

import java.time.LocalDate;


public record ReservationRequestDTO(Long cabinId, LocalDate checkIn, LocalDate checkOut) {
    
}
